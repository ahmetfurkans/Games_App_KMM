package com.svmsoftware.gamesapp.android

import app.cash.sqldelight.db.SqlDriver
import com.svmsoftware.gamesapp.data.local.DatabaseDriverFactory
import com.svmsoftware.gamesapp.db.GamesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val nativeModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }

    single<GamesDatabase> { GamesDatabase(get()) }

    //viewModel { GamesViewModel(get(), get() ,get() ) }
}
