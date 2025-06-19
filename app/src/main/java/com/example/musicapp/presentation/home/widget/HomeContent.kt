package com.example.musicapp.presentation.home.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.R
import com.example.musicapp.presentation.home.mvi.HomeState
import com.example.musicapp.presentation.home.component.HomeHeader
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun HomeContent(
    state: HomeState,
    onSongClicked: () -> Unit,
    onAlbumClicked: () -> Unit,
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
            onItemClicked = {}
        )
    }
}