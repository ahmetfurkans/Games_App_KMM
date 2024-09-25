package com.svmsoftware.gamesapp.presentation.wishlist

import com.svmsoftware.gamesapp.domain.model.Game

data class WishlistState(
    val games: List<Game> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)