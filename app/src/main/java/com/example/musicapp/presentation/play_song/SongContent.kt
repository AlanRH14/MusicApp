package com.example.musicapp.presentation.play_song

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.utils.ValidateFormat.toDecimalValue

@Composable
fun SongContent(
    title: String,
    genre: String,
    image: String,
    duration: Long,
    currentPosition: Long,
    isPlaying: Boolean = false,
    isBuffering: Boolean = false,
    onSeekChange: (newValue: Float) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MusicAppImage(
            modifier = Modifier.size(300.dp),
            pathImage = image,
            imageDefault = 0,
            placeHolder = {},
            contentDescription = stringResource(R.string.song_cover_image),
        )

        Spacer(modifier = Modifier.padding(PaddingDefault))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = genre,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.padding(PaddingDefault))

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
                    style = MaterialTheme.typography.bodySmall
                )

                Box(Modifier.weight(1F))

                Text(
                    text = duration.toDecimalValue(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}