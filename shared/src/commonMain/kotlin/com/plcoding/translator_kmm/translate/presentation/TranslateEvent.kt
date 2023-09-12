package com.plcoding.translator_kmm.translate.presentation

import com.plcoding.translator_kmm.core.domain.language.Language

// sealed class aka super powered enum
sealed class TranslateEvent {
    data class ChoosedFromLanguage(val language: Language) : TranslateEvent()
    data class ChoosedToLanguage(val language: Language) : TranslateEvent()
    object StopChoosingLanguage : TranslateEvent()
    object SwapLanguages : TranslateEvent()
    data class ChangeTranslateText(val text: String) : TranslateEvent()
    object Translate : TranslateEvent()
    object OpenFromLanguageDropDown : TranslateEvent()
    object OpenToLanguageDropDown : TranslateEvent()
    object CloseTranslation : TranslateEvent()
    data class SelectHistoryItem(val item: UiHistoryItem) : TranslateEvent()
    object EditTranslation : TranslateEvent()
    object RecordAudio : TranslateEvent()
    data class SubmitVoiceResult(val text: String) : TranslateEvent()
    object OnErrorSeen : TranslateEvent()
}