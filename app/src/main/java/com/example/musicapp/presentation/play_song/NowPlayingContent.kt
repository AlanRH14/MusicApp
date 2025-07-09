package com.example.musicapp.presentation.play_song

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NowPlayingContent(
    title: String,
    isPlayList: Boolean = false
) {
    val msg = if (isPlayList) "Playing from Playlist" else "Now Playing"

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = msg.uppercase(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}