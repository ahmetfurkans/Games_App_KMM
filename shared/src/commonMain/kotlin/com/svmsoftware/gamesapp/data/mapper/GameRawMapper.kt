package com.svmsoftware.gamesapp.data.mapper

import com.svmsoftware.gamesapp.data.model.GameRaw
import com.svmsoftware.gamesapp.domain.model.Game

fun GameRaw.toGame(isSaved: Boolean) = Game(
    uid = this.uid,
    name = this.name,
    backgroundImg = this.backgroundImg?: "",
    isWishlist = isSaved
)