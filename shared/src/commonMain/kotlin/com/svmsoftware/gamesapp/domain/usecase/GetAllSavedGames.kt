package com.svmsoftware.gamesapp.domain.usecase

import com.svmsoftware.gamesapp.data.repository.GamesRepository
import com.svmsoftware.gamesapp.domain.model.Game

class GetAllSavedGames(private val repository: GamesRepository) {

    fun getAllSavedGames(): List<Game> {
        return repository.getAllSavedGames()
    }
}