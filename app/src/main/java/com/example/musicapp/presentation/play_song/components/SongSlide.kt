package com.example.musicapp.presentation.play_song.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.musicapp.ui.theme.PaddingSmall
import com.example.musicapp.utils.ValidateFormat.toFormattedTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongSlide(
    isPlaying: Boolean,
    duration: Long,
    currentPosition: Long,
    isBuffering: Boolean = false,
    onSeekChange: (newValue: Long) -> Unit,
) {
    var sliderPosition by remember(currentPosition) { mutableLongStateOf(currentPosition) }
    var isUserDragging by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(key1 = isPlaying, key2 = isUserDragging) {
        if (isPlaying && !isUserDragging) {
            while (isActive) {
                sliderPosition = (sliderPosition + 200).coerceAtMost(duration)
                delay(200.milliseconds)
            }
        }
    }

    if (isBuffering) {
        Box { LinearProgressIndicator(modifier = Modifier.fillMaxWidth()) }
    } else {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = if (isUserDragging) {
                sliderPosition.toFloat()
            } else {
                currentPosition.toFloat()
            },
            onValueChange = { newValue ->
                isUserDragging = true
                sliderPosition = newValue.toLong()
                onSeekChange(newValue.toLong())
            },
            onValueChangeFinished = {
                isUserDragging = false
                onSeekChange(sliderPosition)
            },
            valueRange = 0F..duration.coerceAtLeast(1L).toFloat(),
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary
                    ),
                    thumbSize = DpSize(12.dp, 12.dp)
                )
            },
            track = { sliderState ->
                SliderDefaults.Track(
                    colors = SliderDefaults.colors(
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.surface
                    ),
                    sliderState = sliderState,
                    modifier = Modifier.height(4.dp)
                )
            }
        )

        Row(
            modifier = Modifier
                .padding(horizontal = PaddingSmall)
        ) {
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
