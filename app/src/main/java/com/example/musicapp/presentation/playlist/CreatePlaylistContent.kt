package com.example.musicapp.presentation.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.musicapp.presentation.create_playlist.mvi.CreatePlaylistState
import com.example.musicapp.presentation.create_playlist.mvi.CreatePlaylistUIEvent

@Composable
fun CreatePlaylistContent(
    state: CreatePlaylistState,
    onEvent: (CreatePlaylistUIEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("No playlist found")
    }
}