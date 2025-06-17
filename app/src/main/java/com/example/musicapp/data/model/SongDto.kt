package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongDto(
    @SerialName("artist") val artist: ArtistDto? = ArtistDto(),
    @SerialName("audioUrl") val audioUrl: String? = "",
    @SerialName("coverImage") val coverImage: String? = "",
    @SerialName("createdAt") val createdAt: Long? = 0L,
    @SerialName("duration") val duration: Int? = 0,
    @SerialName("genre") val genre: String? = "",
    @SerialName("id") val id: String? = "",
    @SerialName("releaseDate") val releaseDate: Long? = 0L,
    @SerialName("title") val title: String? = "",
    @SerialName("updateAt") val updateAt: Long? = 0L,
)
