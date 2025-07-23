package com.example.musicapp.presentation.playlist_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.musicapp.presentation.common.widgets.ErrorScreen
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaylistDetailScreen(
    navController: NavHostController,
    playlistID: String,
    viewModel: PlaylistDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(PlaylistDetailUIEvent.OnGetPlaylistDetail(playlistID = playlistID))
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaylistDetailEffect.ShowMessageError -> {}
            }
        }
    }

    state.playlist?.let { playlist ->
        PlaylistDetailContent(
            playlist = playlist,
            onEvent = viewModel::onEvent
        )
    }

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.error ?: "Unknown",
            primaryButton = "Retry",
            onPrimaryButtonClicked = {}
        )
    }
}