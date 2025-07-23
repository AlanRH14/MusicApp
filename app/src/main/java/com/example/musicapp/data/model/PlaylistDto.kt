package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistDto(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("coverImage") val coverImage: String? = null,
    @SerialName("userId") val userId: String? = null,
    @SerialName("songs") val songs: List<SongDto>? = null,
    @SerialName("songCount") val songCount: Int? = null,
    @SerialName("createdAt") val createdAt: Long? = null,
    @SerialName("updateAt") val updateAt: Long? = null
)
