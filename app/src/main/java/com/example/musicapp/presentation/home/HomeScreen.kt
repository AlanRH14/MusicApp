package com.example.musicapp.presentation.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.navigation.PlaySong
import com.example.musicapp.navigation.Playlist
import com.example.musicapp.presentation.home.widgets.HomeContent
import com.example.musicapp.presentation.common.widgets.ErrorScreen
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import com.example.musicapp.presentation.home.mvi.HomeEffect
import com.example.musicapp.presentation.home.mvi.HomeUIEvent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(HomeUIEvent.GetHomeData)

        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeEffect.ShowMessage -> {
                    Toast.makeText(navController.context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is HomeEffect.NavigateToPlaySong -> {
                    navController.navigate(PlaySong(songID = effect.songID))
                }

                is HomeEffect.NavigateToPlaylist -> {
                    navController.navigate(Playlist)
                }
            }
        }
    }

    HomeContent(
        state = state,
        onEvent = viewModel::onEvent,
    )

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.error ?: stringResource(id = R.string.unknown),
            primaryButton = stringResource(id = R.string.retry),
            onPrimaryButtonClicked = { viewModel.onEvent(HomeUIEvent.GetHomeData) },
        )
    }
}