package com.svmsoftware.gamesapp.domain.usecase

import com.svmsoftware.gamesapp.data.repository.GamesRepository
import com.svmsoftware.gamesapp.domain.model.Game
import com.svmsoftware.gamesapp.domain.model.Platform

class GamesUseCase(private val repository: GamesRepository) {

    suspend fun getGames(query: String, platform: Platform?): List<Game> {
        return repository.getGames(query, platform)
    }
}