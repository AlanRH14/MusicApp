package com.example.musicapp.presentation.play_song.mvi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.utils.ValidateFormat.toDecimalValue

@Composable
fun SongActions() {
    if (isBuffering) {
        LinearProgressIndicator()
    } else {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = currentPosition.toFloat(),
            onValueChange = onSeekChange,
            valueRange = 0F..duration.toFloat(),
            enabled = !isBuffering
        )

        Row {
            Text(
                text = currentPosition.toDecimalValue(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Box(Modifier.weight(1F))

            Text(
                text = duration.toDecimalValue(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}