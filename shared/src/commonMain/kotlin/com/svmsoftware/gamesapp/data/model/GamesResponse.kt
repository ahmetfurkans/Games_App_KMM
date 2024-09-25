package com.svmsoftware.gamesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponse(
    @SerialName("next")
    val nextUrl: String?,
    @SerialName("previous")
    val previousUrl: String?,
    @SerialName("results")
    val games: List<GameRaw>,
)