package com.example.musicapp.presentation.create_playlist.mvi

sealed interface CreatePlaylistEffect {
    data class ShowErrorMessage(val message: String) : CreatePlaylistEffect
}