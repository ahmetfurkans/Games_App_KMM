package com.svmsoftware.gamesapp.data.mapper

import com.svmsoftware.gamesapp.data.model.PlatformRaw
import com.svmsoftware.gamesapp.domain.model.Platform

fun PlatformRaw.toPlatform() = Platform(
    uid = this.uid,
    name = this.name,
    backgroundImg = this.backgroundImg?: ""
)