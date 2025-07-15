package com.example.musicapp.presentation.play_song.mvi

import androidx.compose.runtime.Composable

@Composable
fun SongActions(
    duration: Long,
    currentPosition: Long,
    isPlaying: Boolean = false,
    isBuffering: Boolean = false,
    onSeekChange: (newValue: Float) -> Unit
) {


}