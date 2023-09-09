package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

class IOSMutableStateFlow<T>(initValue: T) : CommonMutableStateFlow<T>(MutableStateFlow(initValue))