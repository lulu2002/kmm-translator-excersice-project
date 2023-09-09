package com.plcoding.translator_kmm.translate.domain.translate

import com.plcoding.translator_kmm.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(from: Language, to: Language, text: String): String
}