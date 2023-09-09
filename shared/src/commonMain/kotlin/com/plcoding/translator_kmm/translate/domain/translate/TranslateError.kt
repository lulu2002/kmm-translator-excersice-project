package com.plcoding.translator_kmm.translate.domain.translate

enum class TranslateError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN
}


class TranslateException(error: TranslateError) :
    Exception(message = "An error occurred when translating: $error")