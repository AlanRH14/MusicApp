package com.example.musicapp.presentation.play_song.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R

@Composable
fun SongActions(
    isPlaying: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {}
        ) {
            Icon(
                painter = painterResource(android.R.drawable.ic_media_previous),
                contentDescription = stringResource(R.string.media_previous),
            )
        }



        Icon(
            painter = painterResource(
                id = if (isPlaying) {
                    android.R.drawable.ic_media_pause
                } else {
                    android.R.drawable.ic_media_play
                },
            ),
            contentDescription = stringResource(R.string.play_pause),
        )

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(android.R.drawable.ic_media_next),
                contentDescription = stringResource(R.string.next),
            )
        }
    }
}