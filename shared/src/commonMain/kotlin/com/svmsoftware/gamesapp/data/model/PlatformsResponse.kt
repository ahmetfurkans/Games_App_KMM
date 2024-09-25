package com.svmsoftware.gamesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformsResponse(
    @SerialName("next")
    val nextUrl: String?,
    @SerialName("previous")
    val previousUrl: String?,
    @SerialName("results")
    val platforms: List<PlatformRaw>,
)