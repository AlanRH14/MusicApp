package com.example.musicapp.presentation.playlist_detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.musicapp.presentation.common.widgets.ErrorScreen
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import com.example.musicapp.presentation.playlist_detail.mvi.PlaylistDetailEffect
import com.example.musicapp.presentation.playlist_detail.mvi.PlaylistDetailUIEvent
import com.example.musicapp.presentation.playlist_detail.widgets.PlaylistDetailContent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaylistDetailScreen(
    navController: NavHostController,
    playlistID: String,
    viewModel: PlaylistDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = playlistID) {
        viewModel.onEvent(PlaylistDetailUIEvent.OnGetPlaylistDetail(playlistID = playlistID))
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaylistDetailEffect.ShowMessage -> {
                    Toast.makeText(navController.context, effect.message, Toast.LENGTH_SHORT).show()
                }
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
