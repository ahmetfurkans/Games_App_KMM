package com.svmsoftware.gamesapp.domain.model

data class Game(
    val uid: Int,
    val name: String,
    val backgroundImg: String,
    var isWishlist: Boolean,
    )
