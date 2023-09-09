package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.asCommonMutableStateFlow(): CommonMutableStateFlow<T> = CommonMutableStateFlow(this)
expect open class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>) : MutableStateFlow<T>