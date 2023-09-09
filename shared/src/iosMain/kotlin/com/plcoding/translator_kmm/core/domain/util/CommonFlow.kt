package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

actual open class CommonFlow<T> actual constructor(private val flow: Flow<T>) : Flow<T> by flow {

    fun subscribe(
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onCollect: (T) -> Unit
    ): AppDisposableHandle {
        val job = scope.launch(dispatcher) { flow.collect(onCollect) }
        return AppDisposableHandle { job.cancel() }
    }

}