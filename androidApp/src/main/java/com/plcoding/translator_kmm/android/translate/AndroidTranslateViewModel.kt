package com.plcoding.translator_kmm.android.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.translator_kmm.core.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.translate.domain.translate.TranslateUseCase
import com.plcoding.translator_kmm.translate.presentation.TranslateEvent
import com.plcoding.translator_kmm.translate.presentation.TranslateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    private val translate: TranslateUseCase,
    private val historyDataSource: HistoryDataSource
) : ViewModel() {

    private val sharedViewModel by lazy {
        TranslateViewModel(translate, historyDataSource, viewModelScope)
    }

    val state = sharedViewModel.state

    fun onEvent(event: TranslateEvent) {
        sharedViewModel.onEvent(event)
    }

}