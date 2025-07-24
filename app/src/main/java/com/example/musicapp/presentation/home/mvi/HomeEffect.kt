package com.example.musicapp.presentation.home.mvi

sealed interface HomeEffect {
    data class ShowMessage(val message: String) : HomeEffect
    data class NavigationToPlaySong(val songID: String): HomeEffect
    data object NavigationToPlaylist: HomeEffect
}