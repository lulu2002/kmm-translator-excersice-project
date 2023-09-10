package com.plcoding.translator_kmm.core.domain.history

import com.plcoding.translator_kmm.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun addHistoryItem(item: HistoryItem)
}