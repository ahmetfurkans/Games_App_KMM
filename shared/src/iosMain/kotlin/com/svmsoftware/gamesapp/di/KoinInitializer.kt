package com.svmsoftware.gamesapp.di

import com.svmsoftware.gamesapp.presentation.game.GameDetailViewModel
import com.svmsoftware.gamesapp.presentation.games.GamesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedKoinModules

    startKoin {
        modules(modules)
    }
}

class GamesInjector : KoinComponent {
    val gamesViewModel: GamesViewModel by inject()
    val gameDetailViewModel: GameDetailViewModel by inject()
}