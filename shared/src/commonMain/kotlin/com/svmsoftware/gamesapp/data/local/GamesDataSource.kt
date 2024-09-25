package com.svmsoftware.gamesapp.data.local

import com.svmsoftware.gamesapp.data.model.GameRaw
import com.svmsoftware.gamesapp.db.GamesDatabase
import com.svmsoftware.gamesapp.domain.model.Game

class GamesDataSource(private val database: GamesDatabase) {

    fun getAllGames(): List<GameRaw> =
        database.gamesDatabaseQueries.getAllGames(::mapToArticleGameRaw).executeAsList()

    fun insertGame(game: Game) {
        database.gamesDatabaseQueries.insertOrReplaceGame(
            game.uid.toLong(), game.name, game.backgroundImg
        )
    }

    fun checkGameIsOnWishList(id: Int): Boolean {
        val game = database.gamesDatabaseQueries.getGameById(id.toLong(), ::mapToArticleGameRaw)
            .executeAsList()
        return game.isNotEmpty()
    }

    fun deleteGame(uid: Int) {
        database.gamesDatabaseQueries.deleteGame(uid.toLong())
    }

    private fun mapToArticleGameRaw(
        id: Long,
        name: String,
        backgroundUrl: String?,
    ): GameRaw = GameRaw(
        id.toInt(), name, backgroundUrl
    )
}