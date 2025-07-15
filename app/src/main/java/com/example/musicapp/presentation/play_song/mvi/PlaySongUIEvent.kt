package com.example.musicapp.presentation.play_song.mvi

sealed interface PlaySongUIEvent {
    data class GetSongByID(val songID: String) : PlaySongUIEvent
    data object OnToggleToPause: PlaySongUIEvent
    data class OnSeekTo(val position: Long): PlaySongUIEvent
}