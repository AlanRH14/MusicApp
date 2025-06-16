package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponseDto(
    @SerialName("continueListening")
    val continueListeningDto: List<ContinueListeningDto>? = null,
    @SerialName("recommendedSongs")
    val recommendedSongDtos: List<SongDto>? = null,
    @SerialName("topMixes")
    val topMixes: List<AlbumDto>? = null
)