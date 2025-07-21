package com.example.musicapp.presentation.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.musicapp.presentation.playlist.mvi.PlaylistState
import com.example.musicapp.presentation.playlist.mvi.PlaylistUIEvent

@Composable
fun PlaylistContent(
    state: PlaylistState,
    onEvent: (PlaylistUIEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.playlist.isEmpty()) {
            Text("No playlist found")
        }

        TextButton(onClick = {
            onEvent(PlaylistUIEvent.NavigateToCreatePlaylist)
        }) {
            Text(text = "Create Playlist")
        }

        state.playlist.forEach { playlist ->
            Text(playlist.name)
        }
    }
}