package com.svmsoftware.gamesapp.domain.usecase

import com.svmsoftware.gamesapp.data.repository.GamesRepository
import com.svmsoftware.gamesapp.domain.model.Game

class ToggleGameWishlist(private val repository: GamesRepository) {

    fun toggleGameWishlist(game: Game){
        if(game.isWishlist) repository.deleteGameFromWishlist(game)
        else repository.saveGameToWishlist(game)
    }
}