package com.example.musicapp.presentation.play_song

sealed interface PlaySongEffect {
    data class ShowErrorMessage(val message: String): PlaySongEffect
}