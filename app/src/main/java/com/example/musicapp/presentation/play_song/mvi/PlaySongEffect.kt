package com.example.musicapp.presentation.play_song.mvi

sealed interface PlaySongEffect {
    data class ShowMessage(val message: String): PlaySongEffect
}