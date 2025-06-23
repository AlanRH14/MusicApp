package com.example.musicapp.presentation.home.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.R
import com.example.musicapp.domain.model.Home
import com.example.musicapp.presentation.home.mvi.HomeState
import com.example.musicapp.presentation.home.component.HomeHeader
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
        )

        ContinueListeningSection(
            songs = state.homData.continueListening,
            onSongClicked = { id -> onEvent(HomeUIEvent.OnSongClicked(id)) }
        )

        Spacer(modifier = Modifier.size(PaddingLarge))

        TopMixesSection(
            albums = state.homData.topMixes,
            onAlbumClicked = { id -> onEvent(HomeUIEvent.OnAlbumClicked(id)) }
        )

        Spacer(modifier = Modifier.size(PaddingLarge))

        RecommendationSection(
            songs = state.homData.recommendedSong,
            onRecommendationClicked = onEvent(HomeUIEvent.OnSongClicked())
        )
    }
}