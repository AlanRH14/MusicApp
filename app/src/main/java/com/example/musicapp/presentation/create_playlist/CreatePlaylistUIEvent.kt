package com.example.musicapp.presentation.create_playlist

interface CreatePlaylistUIEvent {
    data class ShowErrorMessage(val message: String) : CreatePlaylistUIEvent
}