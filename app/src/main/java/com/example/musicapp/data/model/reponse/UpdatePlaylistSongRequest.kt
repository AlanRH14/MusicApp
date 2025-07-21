package com.example.musicapp.data.model.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePlaylistSongRequest(
    @SerialName("songIds")
    val songIds: List<String>? = null
)
