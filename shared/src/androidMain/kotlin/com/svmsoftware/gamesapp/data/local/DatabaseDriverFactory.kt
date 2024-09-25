package com.svmsoftware.gamesapp.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.svmsoftware.gamesapp.db.GamesDatabase

actual class DatabaseDriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = GamesDatabase.Schema,
            context = context,
            name = "Games.Database.db"
        )
}