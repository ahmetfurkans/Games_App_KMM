package com.svmsoftware.gamesapp.domain.model

data class GameDetail(
    val uid: Int,
    val name: String,
    val backgroundImg: String,
    val description: String? = null,
    val metacritic: String? = null,
    val released: String? = null,
    val playtime: String? = null,
    val genres: List<String>,
    val publishers: List<String>,
    val redditUrl: String? = null,
    val webSiteUrl: String? = null,
)
