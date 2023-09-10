package com.plcoding.translator_kmm.translate.data.history

import com.plcoding.translator_kmm.core.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.domain.util.CommonFlow
import com.plcoding.translator_kmm.core.domain.util.asCommonFlow
import com.plcoding.translator_kmm.core.domain.util.currentTimeMillis
import com.plcoding.translator_kmm.core.domain.util.mapSubList
import com.plcoding.translator_kmm.database.TranslateDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList

class SqlDelightHistoryDataSource(private val db: TranslateDatabase) : HistoryDataSource {

    private val q = db.translateQueries

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return q.getHistory()
            .asFlow()
            .mapToList()
            .mapSubList { it.toHistoryItem() }
            .asCommonFlow()
    }

    override suspend fun addHistoryItem(item: HistoryItem) {
        q.insertHistoryEntity(
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = currentTimeMillis()
        )
    }

}