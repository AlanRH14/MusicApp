package com.example.musicapp.presentation.playlist_detail.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.presentation.playlist_detail.components.PlaylistDetailBigItem
import com.example.musicapp.presentation.playlist_detail.components.PlaylistDetailItem
import com.example.musicapp.presentation.playlist_detail.mvi.PlaylistDetailUIEvent

@Composable
fun PlaylistDetailContent(
    playlist: Playlist,
    onEvent: (PlaylistDetailUIEvent) -> Unit
) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                playlist.songs.firstOrNull()?.let { song ->
                    PlaylistDetailBigItem(
                        title = song.title,
                        coverImage = song.coverImage
                    )
                }
            }

            items(playlist.songs, key = { song -> song.id }) { song ->
                PlaylistDetailItem(
                    song = song,
                    onClick = {
                        onEvent(
                            PlaylistDetailUIEvent.OnDeleteSongOfPlaylist(
                                playlistID = playlist.id,
                                songID = song.id
                            )
                        )
                    }
                )
            }
        }
    }
}
