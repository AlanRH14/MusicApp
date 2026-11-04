package com.example.musicapp.presentation.playlist_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.domain.model.Song
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingSmall

@Composable
fun PlaylistDetailItem(
    song: Song,
    onClick: () -> Unit
) {
    Row(modifier = Modifier.padding(PaddingSmall)) {
        MusicAppImage(
            modifier = Modifier.size(50.dp),
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

        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Song of Playlist Icon",
                tint = Color.Red
            )
        }
    }
}
