package com.example.musicapp.presentation.playlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
        Button(onClick = { onEvent(PlaylistUIEvent.NavigateToCreatePlaylist) }) {
            Text(text = "Create Playlist")
        }

        if (state.playlist.isEmpty()) {
            Text("No playlist found")
        } else {
            LazyColumn {
                items(state.playlist, key = { playlist -> playlist.id }) { playlist ->
                    PlaylistItem(playlist = playlist) { }
                }
            }
        }
    }
}