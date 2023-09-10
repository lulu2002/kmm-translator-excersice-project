package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.StateFlow

actual open class CommonStateFlow<T> actual constructor(flow: StateFlow<T>) : StateFlow<T> by flow