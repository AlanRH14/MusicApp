package com.example.musicapp.presentation.create_playlist.mvi

sealed interface CreatePlaylistUIEvent {
    data object OnCreatePlaylistClicked : CreatePlaylistUIEvent
    data class OnNameUpdate(val name: String): CreatePlaylistUIEvent
    data class OnDescriptionUpdate(val description: String): CreatePlaylistUIEvent
    data object OnCloseClicked: CreatePlaylistUIEvent
}