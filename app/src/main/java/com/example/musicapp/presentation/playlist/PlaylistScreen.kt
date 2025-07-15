package com.example.musicapp.presentation.playlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController

@Composable
fun PlaylistScreen(
    navController: NavHostController,
    viewModel: PlaylistViewModel,
) {

    val state by viewModel.state.collectAsState()


}