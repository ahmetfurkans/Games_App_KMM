package com.svmsoftware.gamesapp.domain.usecase

import com.svmsoftware.gamesapp.data.repository.GamesRepository
import com.svmsoftware.gamesapp.domain.model.GameDetail


class GameDetailUseCase(private val repository: GamesRepository) {

    suspend fun getGameDetail(uid: Int): GameDetail {
        return repository.getGameDetail(uid)
    }
}