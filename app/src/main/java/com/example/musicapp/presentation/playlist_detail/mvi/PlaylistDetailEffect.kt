package com.example.musicapp.presentation.playlist_detail.mvi

sealed interface PlaylistDetailEffect {
    data class ShowMessage(val message: String): PlaylistDetailEffect
}