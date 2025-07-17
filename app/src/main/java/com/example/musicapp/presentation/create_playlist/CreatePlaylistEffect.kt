package com.example.musicapp.presentation.create_playlist

interface CreatePlaylistEffect {
    data class ShowErrorMessage(val message: String) : CreatePlaylistUIEvent
}