package com.plcoding.translator_kmm.translate.data.local

import android.content.Context
import com.plcoding.translator_kmm.database.TranslateDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TranslateDatabase.Schema, context, "translations.db")
    }
}