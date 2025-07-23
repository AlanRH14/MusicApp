package com.example.musicapp.presentation.playlist_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun PlaylistDetailContent(
    playlist: Playlist,
    onEvent: (PlaylistDetailUIEvent) -> Unit
) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingLarge)
            ) {
                playlist.songs.firstOrNull()?.coverImage?.let { image ->
                    MusicAppImage(
                        modifier = Modifier.size(200.dp),
                        pathImage = image,
                        imageDefault = R.drawable.ic_profile,
                        placeHolder = {},
                        contentDescription = "Playlist Image",
                    )
                }

                playlist.songs.firstOrNull()?.title?.let { title ->
                    Text(
                        modifier = Modifier.align(Alignment.TopCenter),
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            }
        }

        items(playlist.songs, key = { song -> song.id }) { song ->
            Row {
                MusicAppImage(
                    pathImage = song.coverImage,
                    imageDefault = R.drawable.ic_logo,
                    placeHolder = {},
                    contentDescription = "Song Image"
                )

                Column {
                    Text(
                        modifier = Modifier.padding(start = PaddingDefault),
                        text = song.title,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        modifier = Modifier.padding(start = PaddingDefault),
                        text = song.artist.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.weight(1F))

                IconButton(onClick = {
                    onEvent(
                        PlaylistDetailUIEvent.OnDeleteSongOfPlaylist(
                            playlistID = playlist.id,
                            songID = song.id
                        )
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Song of Playlist Icon",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}