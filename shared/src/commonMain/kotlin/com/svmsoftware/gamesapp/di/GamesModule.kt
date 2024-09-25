package com.svmsoftware.gamesapp.di

import com.svmsoftware.gamesapp.data.local.GamesDataSource
import com.svmsoftware.gamesapp.data.remote.GamesService
import com.svmsoftware.gamesapp.data.repository.GamesRepository
import com.svmsoftware.gamesapp.domain.usecase.GameDetailUseCase
import com.svmsoftware.gamesapp.domain.usecase.GamesUseCase
import com.svmsoftware.gamesapp.domain.usecase.GetAllSavedGames
import com.svmsoftware.gamesapp.domain.usecase.PlatformsUseCase
import com.svmsoftware.gamesapp.domain.usecase.ToggleGameWishlist
import com.svmsoftware.gamesapp.presentation.game.GameDetailViewModel
import com.svmsoftware.gamesapp.presentation.games.GamesViewModel
import com.svmsoftware.gamesapp.presentation.wishlist.WishlistViewModel

import org.koin.dsl.module

val gamesModule = module {
    single<GamesService> {
        GamesService(get())
    }
    single<GamesRepository> {
        GamesRepository(get(), get())
    }
    single<GamesUseCase> {
        GamesUseCase(get())
    }
    single<PlatformsUseCase> {
        PlatformsUseCase(get())
    }
    single<ToggleGameWishlist> {
        ToggleGameWishlist(get())
    }
    single<GamesViewModel> {
        GamesViewModel(get(), get(), get())
    }
    single<GameDetailUseCase> {
        GameDetailUseCase(get())
    }
    single<GetAllSavedGames> {
        GetAllSavedGames(get())
    }
    single<GamesDataSource> {
        GamesDataSource(get())
    }
    single<GameDetailViewModel> {
        GameDetailViewModel(get())
    }
    single<WishlistViewModel> {
        WishlistViewModel(get(), get())
    }
}