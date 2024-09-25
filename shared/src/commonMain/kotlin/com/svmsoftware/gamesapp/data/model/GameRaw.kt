package com.svmsoftware.gamesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameRaw(
    @SerialName("id")
    val uid: Int,
    @SerialName("name")
    val name: String,
    @SerialName("background_image")
    val backgroundImg: String? = null,
)