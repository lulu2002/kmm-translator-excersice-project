package com.plcoding.translator_kmm.core.presentation

import com.plcoding.translator_kmm.core.domain.language.Language

actual class UiLanguage(
    actual val language: Language,
    val imageName: String
) {
    actual companion object {

        actual fun byCode(code: String): UiLanguage {
            return allLanguages.first { it.language.langCode == code }
        }

        actual val allLanguages: List<UiLanguage> = Language.values().map {
            UiLanguage(
                language = it,
                imageName = it.name.lowercase()
            )
        }

    }
}