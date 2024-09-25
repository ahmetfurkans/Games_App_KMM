package com.svmsoftware.gamesapp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.svmsoftware.gamesapp.db.GamesDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = GamesDatabase.Schema,
            name = "Games.Database.db"
        )
}
