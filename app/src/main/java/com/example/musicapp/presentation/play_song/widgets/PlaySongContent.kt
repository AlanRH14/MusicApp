package com.example.musicapp.presentation.play_song.widgets

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicapp.domain.model.AudioStatus
import com.example.musicapp.domain.model.Song
import com.example.musicapp.presentation.play_song.components.SongActions
import com.example.musicapp.presentation.play_song.components.SongSlide
import com.example.musicapp.presentation.play_song.mvi.PlaySongState
import com.example.musicapp.presentation.play_song.mvi.PlaySongUIEvent
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun PlaySongContent(
    state: PlaySongState,
    onEvent: (PlaySongUIEvent) -> Unit,
) {

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(PaddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderPlaySong(
                title = state.audioStatus.currentSong?.title ?: "",
                isPlayList = state.audioStatus.isPlaying
            )

            Spacer(modifier = Modifier.height(PaddingDefault))

            SongContent(
                title = state.audioStatus.currentSong?.title ?: "",
                genre = state.audioStatus.currentSong?.genre ?: "",
                image = state.audioStatus.currentSong?.coverImage ?: "",
            )

            Spacer(modifier = Modifier.height(PaddingDefault))

            SongSlide(
                isPlaying = state.audioStatus.isPlaying,
                duration = state.audioStatus.duration,
                currentPosition = state.audioStatus.currentPosition,
                isBuffering = state.audioStatus.isBuffering,
                onSeekChange = { onEvent(PlaySongUIEvent.OnSeekTo(position = it)) },
            )

            Spacer(modifier = Modifier.height(PaddingDefault))

            SongActions(
                isPlaying = state.audioStatus.isPlaying,
                onPlayPauseToggle = { onEvent(PlaySongUIEvent.OnToggleToPause) },
                onNextClicked = {},
                onPreviousClicked = {},
                onAddPlaylistClicked = { onEvent(PlaySongUIEvent.OnAddPlaylistClicked) }
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PlaySongContentPreview() {
    MusicAppTheme {
        PlaySongContent(
            state = PlaySongState(
                song = Song(
                    title = "Song Title",
                    genre = "Pop",
                    coverImage = "http://example.com/image.jpg"
                ),
                audioStatus = AudioStatus(
                    currentPosition = 300000L,
                    duration = 300000L,
                    isBuffering = false,
                    isPlaying = true,
                ),
            ),
            onEvent = {}
        )
    }
}
