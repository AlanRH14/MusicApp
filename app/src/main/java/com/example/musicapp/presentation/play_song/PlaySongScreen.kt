package com.example.musicapp.presentation.play_song

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
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaySongScreen(
    navController: NavHostController,
    viewModel: PlaySongViewModel = koinViewModel(),
    songID: String,
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(PlaySongUIEvent.GetSongByID(id = songID))

        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaySongEffect.ShowErrorMessage -> {
                    Toast.makeText(navController.context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.error ?: "Not Found",
            primaryButton = stringResource(R.string.retry),
            onPrimaryButtonClicked = {},
        )
    }
}