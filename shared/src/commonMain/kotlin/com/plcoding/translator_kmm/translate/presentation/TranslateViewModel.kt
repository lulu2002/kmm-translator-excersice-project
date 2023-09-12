package com.plcoding.translator_kmm.translate.presentation

import com.plcoding.translator_kmm.core.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.domain.util.CommonStateFlow
import com.plcoding.translator_kmm.core.domain.util.Resource
import com.plcoding.translator_kmm.core.domain.util.asCommonStateFlow
import com.plcoding.translator_kmm.core.presentation.UiLanguage
import com.plcoding.translator_kmm.translate.domain.translate.TranslateException
import com.plcoding.translator_kmm.translate.domain.translate.TranslateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translateUseCase: TranslateUseCase,
    private val historyDataSource: HistoryDataSource,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {

    private val _state = MutableStateFlow(TranslateState())
    private var translateJob: Job? = null

    val state: CommonStateFlow<TranslateState> =
        combine(_state, historyDataSource.getHistory()) { state, history ->
            return@combine this.onUpdate(state, history)
        }.stateIn(scope, SharingStarted.WhileSubscribed(5000), TranslateState())
            .asCommonStateFlow()

    fun onEvent(event: TranslateEvent) {
        when (event) {
            is TranslateEvent.ChangeTranslateText -> _state.update {
                it.copy(fromText = event.text)
            }

            is TranslateEvent.ChoosedFromLanguage -> _state.update {
                it.copy(
                    isChoosingFromLanguage = false,
                    fromLanguage = UiLanguage.byCode(event.language.langCode)
                )
            }

            is TranslateEvent.ChoosedToLanguage -> _state.updateAndGet {
                it.copy(
                    isChoosingToLanguage = false,
                    toLanguage = UiLanguage.byCode(event.language.langCode)
                )
            }.also { translate(it) }

            TranslateEvent.CloseTranslation -> _state.update {
                it.copy(
                    isTranslating = false,
                    fromText = "",
                    toText = null
                )
            }

            TranslateEvent.EditTranslation -> {
                if (state.value.toText == null)
                    return

                _state.update {
                    it.copy(
                        toText = null,
                        isTranslating = false
                    )
                }
            }

            TranslateEvent.OnErrorSeen -> {
                _state.update { it.copy(error = null) }
            }

            TranslateEvent.OpenFromLanguageDropDown -> {
                _state.update { it.copy(isChoosingFromLanguage = true) }
            }

            TranslateEvent.OpenToLanguageDropDown -> {
                _state.update { it.copy(isChoosingToLanguage = true) }
            }

            is TranslateEvent.SelectHistoryItem -> {
                translateJob?.cancel()
                _state.update {
                    val item = event.item
                    it.copy(
                        fromText = item.fromText,
                        toText = item.toText,
                        fromLanguage = item.fromLanguage,
                        toLanguage = item.toLanguage
                    )
                }
            }

            TranslateEvent.StopChoosingLanguage -> {
                _state.update {
                    it.copy(
                        isChoosingFromLanguage = false,
                        isChoosingToLanguage = false
                    )
                }
            }

            is TranslateEvent.SubmitVoiceResult -> {
                _state.update {
                    it.copy(
                        fromText = event.text,
                        isTranslating = if (event.text.isNotBlank()) true else it.isTranslating,
                        toText = if (event.text.isNotBlank()) null else it.toText
                    )
                }
            }

            TranslateEvent.SwapLanguages -> {
                _state.update {
                    it.copy(
                        fromLanguage = it.toLanguage,
                        toLanguage = it.fromLanguage,
                        fromText = it.toText ?: "",
                        toText = if (it.toText != null) it.fromText else null
                    )
                }
            }

            TranslateEvent.Translate -> translate(state.value)
            else -> {}
        }
    }


    private fun translate(state: TranslateState) {
        if (state.isTranslating || state.fromText.isBlank())
            return

        translateJob = scope.launch {
            _state.update { it.copy(isTranslating = true) }
            val result = translateUseCase.execute(
                fromLanguage = state.fromLanguage.language,
                fromText = state.fromText,
                toLanguage = state.toLanguage.language
            )

            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            toText = result.data
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            error = (result.throwable as TranslateException).errorType
                        )
                    }
                }
            }
        }
    }

    private fun onUpdate(state: TranslateState, history: List<HistoryItem>): TranslateState {
        if (history == state.history)
            return state

        return state.copy(
            history = history.mapNotNull { item ->
                UiHistoryItem(
                    id = item.id ?: return@mapNotNull null,
                    fromText = item.fromText,
                    toText = item.toText,
                    fromLanguage = UiLanguage.byCode(item.fromLanguageCode),
                    toLanguage = UiLanguage.byCode(item.toLanguageCode)
                )
            }
        )
    }

}