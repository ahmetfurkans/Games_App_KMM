package com.svmsoftware.gamesapp.domain.model

data class Platform(
    val uid: Int,
    val name: String,
    val backgroundImg: String? = null,
)