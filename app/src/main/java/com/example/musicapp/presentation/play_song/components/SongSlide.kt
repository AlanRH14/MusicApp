package com.example.musicapp.presentation.play_song.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.utils.ValidateFormat.toFormattedTime

@Composable
fun SongSlide(
    duration: Long,
    currentPosition: Long,
    isPlaying: Boolean = false,
    isBuffering: Boolean = false,
    onSeekChange: (newValue: Long) -> Unit,
    onPlayPauseToggle: () -> Unit,
    onNextClicked: () -> Unit = {},
    onPreviousClicked: () -> Unit = {},
) {
    if (isBuffering) {
        LinearProgressIndicator()
    } else {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = currentPosition.toFloat(),
            onValueChange = { onSeekChange.invoke(it.toLong()) },
            valueRange = 0F..duration.toFloat(),
            enabled = !isBuffering
        )

        Row {
            Text(
                text = currentPosition.toFormattedTime(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Box(modifier = Modifier.weight(1F))

            Text(
                text = duration.toFormattedTime(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}