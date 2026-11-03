package com.example.musicapp.presentation.playlist.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppButton
import com.example.musicapp.presentation.home.components.HomeHeader
import com.example.musicapp.presentation.playlist.components.PlaylistItem
import com.example.musicapp.presentation.playlist.mvi.PlaylistState
import com.example.musicapp.presentation.playlist.mvi.PlaylistUIEvent
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun PlaylistContent(
    state: PlaylistState,
    onEvent: (PlaylistUIEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(PaddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        HomeHeader(
            title = stringResource(id = R.string.create_playlist),
            userImage = "",
            userImageDefault = R.drawable.ic_profile,
            onPlaylistButtonClicked = {  }
        )

        MusicAppButton(
            modifier = Modifier.padding(PaddingLarge),
            text = stringResource(id = R.string.create_playlist),
            onClick = { onEvent(PlaylistUIEvent.OnCreatePlaylistClicked) }
        )

        if (state.playlist.isEmpty()) {
            Text(stringResource(id = R.string.no_playlist_found))
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
