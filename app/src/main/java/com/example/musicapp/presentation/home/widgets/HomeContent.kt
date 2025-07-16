package com.example.musicapp.presentation.home.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.R
import com.example.musicapp.presentation.home.mvi.HomeState
import com.example.musicapp.presentation.home.components.HomeHeader
import com.example.musicapp.presentation.home.mvi.HomeUIEvent
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeUIEvent) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = PaddingLarge)
            .fillMaxSize()
    ) {
        HomeHeader(
            userName = state.user.name,
            userImage = state.user.profilePicture,
            userImageDefault = R.drawable.ic_profile,
            onPlaylistButtonClicked = { onEvent(HomeUIEvent.OnPlaylistButtonClicked) }
        )

        ContinueListeningSection(
            songs = state.homData.continueListening,
            onSongClicked = { songID -> onEvent(HomeUIEvent.OnSongClicked(songID = songID)) }
        )

        Spacer(modifier = Modifier.size(PaddingLarge))

        TopMixesSection(
            albums = state.homData.topMixes,
            onAlbumClicked = { albumID -> onEvent(HomeUIEvent.OnAlbumClicked(albumID = albumID)) }
        )

        Spacer(modifier = Modifier.size(PaddingLarge))

        SongRecommendationSection(
            songs = state.homData.recommendedSong,
            onRecommendationClicked = { songID ->
                onEvent(
                    HomeUIEvent.OnSongRecommendationClicked(
                        songID = songID
                    )
                )
            }
        )
    }
}