package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopMixe(
    @SerialName("artist")
    val artist: Artist?,
    @SerialName("coverImage")
    val coverImage: String?,
    @SerialName("genre")
    val genre: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("releaseDate")
    val releaseDate: Long?,
    @SerialName("songs")
    val songs: List<Song>?,
    @SerialName("title")
    val title: String?
)