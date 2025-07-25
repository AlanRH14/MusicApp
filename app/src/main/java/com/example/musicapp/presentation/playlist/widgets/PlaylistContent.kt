package com.example.musicapp.presentation.playlist.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R
import com.example.musicapp.presentation.playlist.components.PlaylistItem
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
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(PlaylistUIEvent.OnCreatePlaylistClicked) }
        ) {
            Text(text = stringResource(id = R.string.create_playlist))
        }

        if (state.playlist.isEmpty()) {
            Text(stringResource(R.string.no_playlist_found))
        } else {
            LazyColumn {
                items(state.playlist, key = { playlist -> playlist.id }) { playlist ->
                    PlaylistItem(
                        playlist = playlist,
                        onItemClicked = {
                            onEvent(PlaylistUIEvent.OnPlaylistDetailClicked(playlistID = playlist.id))
                        }
                    )
                }
            }
        }
    }
}