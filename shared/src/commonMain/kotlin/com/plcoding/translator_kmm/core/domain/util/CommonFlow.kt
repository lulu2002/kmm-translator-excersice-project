package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)
expect class CommonFlow<T>(flow: Flow<T>) : Flow<T>