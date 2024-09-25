package com.svmsoftware.gamesapp.domain.usecase

import com.svmsoftware.gamesapp.data.repository.GamesRepository
import com.svmsoftware.gamesapp.domain.model.Platform

class PlatformsUseCase(private val repository: GamesRepository) {

    suspend fun getPlatforms(): List<Platform> {
        return repository.getPlatforms()
    }
}