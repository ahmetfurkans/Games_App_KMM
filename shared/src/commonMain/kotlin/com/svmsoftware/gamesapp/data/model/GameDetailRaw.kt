package com.svmsoftware.gamesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailRaw(
    @SerialName("id")
    val uid: Int,
    @SerialName("name")
    val name: String,
    @SerialName("background_image")
    val backgroundImg: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("metacritic")
    val metacritic: Int? = null,
    @SerialName("released")
    val released: String? = null,
    @SerialName("playtime")
    val playtime: Int? = null,
    @SerialName("genres")
    val genres: List<GenreRaw>,
    @SerialName("publishers")
    val publishers: List<PublisherRaw>,
    @SerialName("reddit_url")
    val redditUrl: String? = null,
    @SerialName("website")
    val webSiteUrl: String? = null
)

@Serializable
data class GenreRaw(
    @SerialName("name")
    val name: String,
)

@Serializable
data class PublisherRaw(
    @SerialName("name")
    val name: String,
)

