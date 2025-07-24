package com.example.musicapp.presentation.home.mvi

sealed interface HomeEffect {
    data class ShowMessage(val message: String) : HomeEffect
    data class NavigateToPlaySong(val songID: String): HomeEffect
    data object NavigateToPlaylist: HomeEffect
}