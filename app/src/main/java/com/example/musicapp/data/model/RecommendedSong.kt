package com.example.musicapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedSong(
    @SerialName("artist")
    val artist: ArtistX?,
    @SerialName("audioUrl")
    val audioUrl: String?,
    @SerialName("coverImage")
    val coverImage: String?,
    @SerialName("createdAt")
    val createdAt: Long?,
    @SerialName("duration")
    val duration: Int?,
    @SerialName("genre")
    val genre: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("releaseDate")
    val releaseDate: Long?,
    @SerialName("title")
    val title: String?,
    @SerialName("updatedAt")
    val updatedAt: Long?
)