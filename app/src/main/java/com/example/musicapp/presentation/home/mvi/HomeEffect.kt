package com.example.musicapp.presentation.home.mvi

sealed interface HomeEffect {
    data class ShowErrorMessage(val message: String) : HomeEffect
    data class NavigateToSong(val songID: String): HomeEffect
    data object NavigationToPlaylist: HomeEffect
}