package com.plcoding.translator_kmm.translate.presentation

// sealed class aka super powered enum
sealed class TranslateEvent {
    data class ChoosedFromLanguage(val language: String) : TranslateEvent()
    data class ChoosedToLanguage(val language: String) : TranslateEvent()
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