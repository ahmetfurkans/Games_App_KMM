package com.svmsoftware.gamesapp.data.mapper

import com.svmsoftware.gamesapp.data.model.GameDetailRaw
import com.svmsoftware.gamesapp.domain.model.GameDetail

fun GameDetailRaw.toGameDetail(): GameDetail {
    return GameDetail(
        uid = this.uid,
        name = this.name,
        backgroundImg = this.backgroundImg ?: "",
        description = this.description,
        metacritic = this.metacritic.toString(),
        released = this.released,
        playtime = this.playtime.toString(),
        genres = this.genres.map { it.name },
        publishers = this.publishers.map { it.name },
        webSiteUrl = this.webSiteUrl,
        redditUrl = this.redditUrl
    )
}