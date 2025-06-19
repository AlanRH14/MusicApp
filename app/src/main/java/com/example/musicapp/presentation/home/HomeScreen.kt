package com.example.musicapp.presentation.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.presentation.home.widget.HomeContent
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

        viewModel.event.collectLatest {
            when (it) {
                is HomeEffect.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    HomeContent(
        state = state,
        onSongClicked = {},
        onAlbumClicked = {}
    )

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.error ?: "",
            primaryButton = stringResource(R.string.retry),
            onPrimaryButtonClicked = {},
        )
    }
}