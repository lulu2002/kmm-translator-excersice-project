package com.plcoding.translator_kmm.translate.data.translate

import com.plcoding.translator_kmm.NetworkConstants
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.translate.domain.translate.TranslateClient
import com.plcoding.translator_kmm.translate.domain.translate.TranslateError
import com.plcoding.translator_kmm.translate.domain.translate.TranslateException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class KtorTranslateClient(private val httpClient: HttpClient) : TranslateClient {

    override suspend fun translate(from: Language, to: Language, text: String): String {
        val result = try {
            httpClient.post(NetworkConstants.BASE_URL + "/translate") {
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        text = text,
                        sourceLanguage = from.langCode,
                        targetLanguage = to.langCode,
                    )
                )
            }
        } catch (e: IOException) {
            throw TranslateException(TranslateError.SERVICE_UNAVAILABLE)
        }

        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw TranslateException(TranslateError.SERVER_ERROR)
            in 400..499 -> throw TranslateException(TranslateError.CLIENT_ERROR)
            else -> throw TranslateException(TranslateError.UNKNOWN)
        }

        return try {
            result.body<TranslatedDto>().translatedText
        } catch (e: Exception) {
            throw TranslateException(TranslateError.SERVER_ERROR)
        }
    }

}