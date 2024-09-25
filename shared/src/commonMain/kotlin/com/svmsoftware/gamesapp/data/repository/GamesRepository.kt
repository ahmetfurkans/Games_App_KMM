package com.svmsoftware.gamesapp.data.repository

import com.svmsoftware.gamesapp.data.local.GamesDataSource
import com.svmsoftware.gamesapp.data.mapper.toGame
import com.svmsoftware.gamesapp.data.mapper.toGameDetail
import com.svmsoftware.gamesapp.data.mapper.toPlatform
import com.svmsoftware.gamesapp.data.remote.GamesService
import com.svmsoftware.gamesapp.domain.model.Game
import com.svmsoftware.gamesapp.domain.model.GameDetail
import com.svmsoftware.gamesapp.domain.model.Platform

class GamesRepository(
    private val service: GamesService,
    private val gamesDataSource: GamesDataSource,
) {

    suspend fun getGames(query: String, platform: Platform?): List<Game> {
        return service.fetchGames(query, platform).games.map {
            val isSaved = gamesDataSource.checkGameIsOnWishList(it.uid)
            it.toGame(isSaved)
        }
    }

    suspend fun getPlatforms(): List<Platform> {
        return service.fetchPlatforms().platforms.map { it.toPlatform() }
    }

    suspend fun getGameDetail(uid: Int): GameDetail {
        return service.fetchGameDetail(uid).toGameDetail()
    }

    fun saveGameToWishlist(game: Game) {
        gamesDataSource.insertGame(game)
    }

    fun deleteGameFromWishlist(game: Game) {
        gamesDataSource.deleteGame(game.uid)
    }

    fun getAllSavedGames(): List<Game> {
        return gamesDataSource.getAllGames().map { it.toGame(isSaved = true)}
    }
}
