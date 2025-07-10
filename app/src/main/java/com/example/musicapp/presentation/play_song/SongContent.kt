package com.example.musicapp.presentation.play_song

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault

@Composable
fun SongContent(
    title: String,
    genre: String,
    image: String,
    duration: Long,
    currentPosition: Long,
    isPlaying: Boolean = false,
    isBuffering: Boolean = false
) {
    Column(
        modifier = Modifier
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
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = genre,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}