package com.plcoding.translator_kmm.translate.data.translate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslateDto(
    @SerialName("q") val text: String,
    @SerialName("source") val sourceLanguage: String,
    @SerialName("target") val targetLanguage: String
)