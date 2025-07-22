package com.example.musicapp.presentation.play_song.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.presentation.play_song.mvi.PlaySongUIEvent
import com.example.musicapp.presentation.playlist.components.PlaylistItem
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistBottomSheet(
    sheetState: SheetState,
    playlists: List<Playlist>,
    songID: String,
    onEvent: (PlaySongUIEvent) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onEvent(PlaySongUIEvent.OnToggleToBottomSheet) },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingLarge),
            verticalArrangement = Arrangement.spacedBy(PaddingDefault)
        ) {
            Text(
                text = stringResource(R.string.select_playlist),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            LazyColumn {
                items(items = playlists, key = { playlist -> playlist.id }) { playlist ->
                    PlaylistItem(
                        playlist = playlist,
                        onItemClicked = {
                            onEvent(
                                PlaySongUIEvent.OnAddSongToPlaylist(
                                    playlistID = playlist.id,
                                    songID = songID
                                )
                            )
                            onEvent(PlaySongUIEvent.OnToggleToBottomSheet)
                        }
                    )
                }
            }
        }
    }
}