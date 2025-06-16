package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("continueListening")
    val continueListening: List<ContinueListening>?,
    @SerialName("recommendedSongs")
    val recommendedSongs: List<Song>?,
    @SerialName("topMixes")
    val topMixes: List<Album>?
)