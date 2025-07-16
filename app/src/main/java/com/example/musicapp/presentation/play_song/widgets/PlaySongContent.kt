package com.example.musicapp.presentation.play_song.widgets

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicapp.presentation.play_song.components.SongActions
import com.example.musicapp.presentation.play_song.components.SongSlide
import com.example.musicapp.presentation.play_song.mvi.PlaySongUIEvent
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun PlaySongContent(
    title: String,
    genre: String,
    image: String,
    currentPosition: Long,
    duration: Long,
    isPlaying: Boolean,
    isBuffering: Boolean,
    onEvent: (PlaySongUIEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(PaddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderPlaySong(
            title = title,
            isPlayList = isPlaying
        )

        Spacer(modifier = Modifier.padding(PaddingDefault))

        SongContent(
            title = title,
            genre = genre,
            image = image,
        )

        Spacer(modifier = Modifier.padding(PaddingDefault))

        SongSlide(
            duration = duration,
            currentPosition = currentPosition,
            isBuffering = isBuffering,
            onSeekChange = onEvent,
        )

        Spacer(modifier = Modifier.padding(PaddingDefault))

        SongActions(
            isPlaying = isPlaying,
            onPlayPauseToggle = onPlayPauseToggle,
            onNextClicked = {},
            onPreviousClicked = {}
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PlaySongContentPreview() {
    MusicAppTheme {
        PlaySongContent(
            title = "Song Title",
            genre = "Pop",
            image = "http://example.com/image.jpg",
            currentPosition = 300000L,
            duration = 300000L,
            isBuffering = false,
            isPlaying = true,
            onSeekChange = {},
            onPlayPauseToggle = {}
        )
    }
}