package com.plcoding.translator_kmm.translate.domain.translate

import com.plcoding.translator_kmm.core.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.core.domain.util.Resource

class TranslateUseCase(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {

    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resource<String> {
        return try {
            val translation = client.translate(fromLanguage, toLanguage, fromText)
            historyDataSource.addHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translation
                )
            )
            Resource.Success(translation)
        } catch (e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }

}