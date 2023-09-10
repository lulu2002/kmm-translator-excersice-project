package com.plcoding.translator_kmm.translate.data.history

import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import database.HistoryEntity
import kotlinx.datetime.Clock

fun HistoryEntity.toHistoryItem(): HistoryItem {
    return HistoryItem(
        id = id,
        fromLanguageCode = fromLanguageCode,
        fromText = fromText,
        toLanguageCode = toLanguageCode,
        toText = toText,
    )
}

fun HistoryItem.toHistoryEntity(): HistoryEntity {
    return HistoryEntity(
        id = id,
        fromLanguageCode = fromLanguageCode,
        fromText = fromText,
        toLanguageCode = toLanguageCode,
        toText = toText,
        timestamp = Clock.System.now().toEpochMilliseconds()
    )
}