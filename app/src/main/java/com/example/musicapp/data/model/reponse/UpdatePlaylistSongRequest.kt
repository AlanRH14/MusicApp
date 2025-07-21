package com.example.musicapp.data.model.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePlaylistSongRequest(
    @SerialName("playlistId")
    val playlistId: String? = null,
    @SerialName("songId")
    val songId: String? = null
)
