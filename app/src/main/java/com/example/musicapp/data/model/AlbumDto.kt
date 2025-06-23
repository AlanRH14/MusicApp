package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDto(
    @SerialName("artist") val artist: ArtistDto? = null,
    @SerialName("coverImage") val coverImage: String? = null,
    @SerialName("genre") val genre: String? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("releaseDate") val releaseDate: Long? = null,
    @SerialName("songs") val song: List<SongDto>? = null,
    @SerialName("title") val title: String? = null
)
