package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDto(
    @SerialName("artist") val artist: ArtistDto,
    @SerialName("coverImage") val coverImage: String,
    @SerialName("genre") val genre: String,
    @SerialName("id") val id: String,
    @SerialName("releaseDate") val releaseDate: Long,
    @SerialName("songs") val song: List<SongDto>,
    @SerialName("title") val title: String
)
