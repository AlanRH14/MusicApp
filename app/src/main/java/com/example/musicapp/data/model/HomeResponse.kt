package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("continueListening")
    val continueListening: List<ContinueListening>? = null,
    @SerialName("recommendedSongs")
    val recommendedSongs: List<Song>? = null,
    @SerialName("topMixes")
    val topMixes: List<AlbumDto>? = null
)