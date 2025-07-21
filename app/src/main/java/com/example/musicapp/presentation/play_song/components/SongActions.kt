package com.example.musicapp.presentation.play_song.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = onPreviousClicked) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_media_previous),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(R.string.media_previous),
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
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(R.string.play_pause),
            )
        }

        IconButton(onClick = onNextClicked) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_media_next),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(R.string.next),
            )
        }

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = 0),
                contentDescription = "Playlist Icon",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}