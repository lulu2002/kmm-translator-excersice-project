package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun <T, R> Flow<List<T>>.mapSubList(mapFun: (T) -> R): Flow<List<R>> {
    return map { subList ->
        subList.map(mapFun)
    }
}
