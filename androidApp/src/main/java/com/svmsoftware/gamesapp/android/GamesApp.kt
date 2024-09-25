package com.svmsoftware.gamesapp.android

import android.app.Application
import com.svmsoftware.gamesapp.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GamesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + nativeModule

        startKoin {
            androidContext(this@GamesApp)
            modules(modules)
        }
    }
}