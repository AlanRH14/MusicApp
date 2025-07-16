package com.example.musicapp.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePlaylistRequest(
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("coverImage") val coverImage: String? = null,
    @SerialName("songIds") val songIds: List<String>? = null
)
