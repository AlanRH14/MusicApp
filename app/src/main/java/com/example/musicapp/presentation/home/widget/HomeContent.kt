package com.example.musicapp.presentation.home.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.musicapp.R
import com.example.musicapp.presentation.home.HomeState
import com.example.musicapp.presentation.home.component.HomeHeader

@Composable
fun HomeContent(
    state: HomeState,
    onSongClicked: () -> Unit,
    onAlbumClicked: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeHeader(
            userName = state.userDto.name,
            painterResource(R.drawable.ic_profile)
        )
    }
}