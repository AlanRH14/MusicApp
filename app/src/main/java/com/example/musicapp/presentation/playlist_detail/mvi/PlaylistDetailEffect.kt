package com.example.musicapp.presentation.playlist_detail.mvi

sealed interface PlaylistDetailEffect {
    data class ShowMessageError(val message: String): PlaylistDetailEffect
}