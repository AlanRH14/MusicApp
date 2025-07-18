package com.example.musicapp.presentation.playlist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.presentation.common.widgets.ErrorScreen
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import com.example.musicapp.presentation.playlist.mvi.PlaylistEffect
import com.example.musicapp.presentation.playlist.mvi.PlaylistUIEvent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaylistScreen(
    navController: NavHostController,
    viewModel: PlaylistViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(PlaylistUIEvent.GetPlaylist)

        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaylistEffect.ShowMessageError -> {
                    Toast.makeText(navController.context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    PlaylistContent(
        state = state,
        onEvent = viewModel::onEvent
    )

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.error ?: stringResource(id = R.string.unknown),
            primaryButton = stringResource(id = R.string.retry),
            onPrimaryButtonClicked = {}
        )
    }
}