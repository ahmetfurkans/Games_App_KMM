package com.svmsoftware.gamesapp.data.remote

import com.svmsoftware.gamesapp.data.model.GameDetailRaw
import com.svmsoftware.gamesapp.data.model.GamesResponse
import com.svmsoftware.gamesapp.data.model.PlatformsResponse
import com.svmsoftware.gamesapp.domain.model.Platform
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GamesService(private val httpClient: HttpClient) {
    companion object {
        // TODO = Create Api Key, get from env
        private const val API_KEY = "583c39cf9e3c48a19c3417976c6cc1f4"
    }

    suspend fun fetchGames(query: String, platform: Platform?): GamesResponse {
        val endpoint = buildString {
            append("https://api.rawg.io/api/games?key=$API_KEY&search=$query")
            if (platform != null) append("&platforms=${platform.uid}")
        }

        return httpClient.get(endpoint)
            .body()
    }

    suspend fun fetchPlatforms(): PlatformsResponse {
        val endpoint = buildString {
            append("https://api.rawg.io/api/platforms/lists/parents?key=$API_KEY")
        }
        return httpClient.get(endpoint)
            .body()
    }

    suspend fun fetchGameDetail(uid: Int): GameDetailRaw {
        val endpoint = buildString {
            append("https://api.rawg.io/api/games/$uid?key=$API_KEY")
        }
        return httpClient.get(endpoint)
            .body()
    }
}