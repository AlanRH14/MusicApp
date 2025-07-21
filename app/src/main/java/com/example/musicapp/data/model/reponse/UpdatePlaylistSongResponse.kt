package com.example.musicapp.data.model.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePlaylistSongResponse(
    @SerialName("songsAdded")
    val songsAdded: Int? = null,
    @SerialName("songsRemoved")
    val songsRemoved: Int? = null
)
