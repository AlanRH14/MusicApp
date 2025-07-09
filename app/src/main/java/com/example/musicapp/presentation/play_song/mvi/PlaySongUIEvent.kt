package com.example.musicapp.presentation.play_song.mvi

sealed interface PlaySongUIEvent {
    data class GetSongByID(val songID: String) : PlaySongUIEvent
}