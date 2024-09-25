package com.svmsoftware.gamesapp.presentation.game

import com.svmsoftware.gamesapp.domain.model.GameDetail

data class GameDetailState(
    val game: GameDetail? = null,
    val loading: Boolean = false,
    val error: String? = null,
)