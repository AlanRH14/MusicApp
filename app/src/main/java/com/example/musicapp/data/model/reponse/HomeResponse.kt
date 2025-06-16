package com.example.musicapp.data.model.reponse

import com.example.musicapp.data.model.AlbumDto
import com.example.musicapp.data.model.SongDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("continueListening")
    val continueListening: List<SongDto>? = null,
    @SerialName("recommendedSongs")
    val recommendedSong: List<SongDto>? = null,
    @SerialName("topMixes")
    val topMixes: List<AlbumDto>? = null
)