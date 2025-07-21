package com.example.musicapp.presentation.play_song.mvi

sealed interface PlaySongEffect {
    data class ShowErrorMessage(val message: String): PlaySongEffect
    data class NavigateToPlaylist(val idSong: String): PlaySongEffect
}