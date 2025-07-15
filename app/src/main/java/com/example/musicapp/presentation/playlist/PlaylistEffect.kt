package com.example.musicapp.presentation.playlist

interface PlaylistEffect {
    data class ShowMessageError(val message: String) : PlaylistEffect
}