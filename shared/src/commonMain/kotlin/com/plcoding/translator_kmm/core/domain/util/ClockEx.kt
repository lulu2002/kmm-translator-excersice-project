package com.plcoding.translator_kmm.core.domain.util

import kotlinx.datetime.Clock


fun currentTimeMillis(): Long {
    return Clock.System.now().toEpochMilliseconds()
}