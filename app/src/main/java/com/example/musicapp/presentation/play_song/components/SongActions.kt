package com.example.musicapp.presentation.play_song.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.ui.theme.PaddingDefault

@Composable
fun SongActions(
    isPlaying: Boolean = false,
    onPreviousClicked: () -> Unit = {},
    onPlayPauseToggle: () -> Unit,
    onNextClicked: () -> Unit = {},
    onAddPlaylistClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(modifier = Modifier.size(40.dp))

        Spacer(modifier = Modifier.weight(1F))

        IconButton(onClick = onPreviousClicked) {
            Icon(
                modifier = Modifier
                    .size(80.dp),
                imageVector = Icons.Default.SkipPrevious,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(id = R.string.media_previous),
            )
        }

        IconButton(
            modifier = Modifier
                .padding(horizontal = PaddingDefault)
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            onClick = onPlayPauseToggle
        ) {
            Icon(
                painter = painterResource(
                    id = if (isPlaying) {
                        android.R.drawable.ic_media_pause
                    } else {
                        android.R.drawable.ic_media_play
                    },
                ),
                tint = MaterialTheme.colorScheme.onSecondary,
                contentDescription = stringResource(id = R.string.play_pause),
            )
        }

        IconButton(onClick = onNextClicked) {
            Icon(
                modifier = Modifier
                    .size(80.dp),
                imageVector = Icons.Default.SkipNext,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(id = R.string.next),
            )
        }

        Spacer(modifier = Modifier.weight(1F))

        IconButton(onClick = onAddPlaylistClicked) {
            Icon(
                modifier = Modifier
                    .size(80.dp),
                imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                contentDescription = stringResource(id = R.string.playlist_add),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
