package com.svmsoftware.gamesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformRaw(
    @SerialName("id")
    val uid: Int,
    @SerialName("name")
    val name: String,
    @SerialName("image_background")
    val backgroundImg: String? = null,
)