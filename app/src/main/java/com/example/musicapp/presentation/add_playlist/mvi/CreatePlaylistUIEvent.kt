package com.example.musicapp.presentation.add_playlist.mvi

interface CreatePlaylistUIEvent {
    data object OnCreatePlaylistClicked : CreatePlaylistUIEvent
    data class OnNameUpdate(val name: String): CreatePlaylistUIEvent
    data class OnDescriptionUpdate(val description: String): CreatePlaylistUIEvent
}