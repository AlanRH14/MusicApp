package com.example.musicapp.presentation.playlist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlaylistScreen(
    navController: NavHostController,
    viewModel: PlaylistViewModel,
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaylistEffect.ShowMessageError -> {
                    Toast.makeText(navController.context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    if (state.isLoading) {
        LoadingScreen()
    }
}