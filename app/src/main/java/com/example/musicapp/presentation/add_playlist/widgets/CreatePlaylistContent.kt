package com.example.musicapp.presentation.add_playlist.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppTextField
import com.example.musicapp.presentation.add_playlist.mvi.CreatePlaylistState
import com.example.musicapp.presentation.add_playlist.mvi.CreatePlaylistUIEvent
import com.example.musicapp.ui.theme.PaddingDefault

@Composable
fun CreatePlaylistContent(
    state: CreatePlaylistState,
    onEvent: (CreatePlaylistUIEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingDefault),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MusicAppTextField(
            value = state.name,
            onValueChange = { onEvent(CreatePlaylistUIEvent.OnNameUpdate(name = it)) },
            label = stringResource(R.string.playlist_name),
            placeholder = "Enter playlist name",
            isError = state.isNameEmpty
        )

        MusicAppTextField(
            value = state.description,
            onValueChange = { onEvent(CreatePlaylistUIEvent.OnDescriptionUpdate(description = it)) },
            label = stringResource(R.string.description),
            placeholder = "Enter playlist description",
            isError = state.isDescriptionEmpty
        )

        Spacer(modifier = Modifier.weight(1F))

        Button(onClick = { onEvent(CreatePlaylistUIEvent.OnCreatePlaylistClicked) }) {
            Text(text = stringResource(R.string.create_playlist))
        }
    }
}