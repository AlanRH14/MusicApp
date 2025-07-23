package com.example.musicapp.presentation.add_playlist.mvi

sealed interface CreatePlaylistUIEvent {
    data object OnCreatePlaylistClicked : CreatePlaylistUIEvent
    data class OnNameUpdate(val name: String): CreatePlaylistUIEvent
    data class OnDescriptionUpdate(val description: String): CreatePlaylistUIEvent
}