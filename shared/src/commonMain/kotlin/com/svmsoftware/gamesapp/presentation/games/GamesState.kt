package com.svmsoftware.gamesapp.presentation.games

import com.svmsoftware.gamesapp.domain.model.Game
import com.svmsoftware.gamesapp.domain.model.Platform

data class GamesState(
    val games: List<Game> = emptyList(),
    val platforms: List<Platform> = emptyList(),
    val selectedPlatformIndex: Int = -1,
    val loading: Boolean = false,
    val error: String? = null,
    val query: String = "",
    val isHintVisible: Boolean = false,
)