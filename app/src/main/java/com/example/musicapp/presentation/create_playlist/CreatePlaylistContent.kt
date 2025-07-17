package com.example.musicapp.presentation.create_playlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppTextField
import com.example.musicapp.ui.theme.PaddingDefault

@Composable
fun CreatePlaylistContent(
    state: CreatePlaylistState
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(PaddingDefault)
    ) {

        MusicAppTextField(
            value = state.name,
            onValueChange = {},
            label = stringResource(R.string.playlist_name),
            placeholder = "Enter playlist name"
        )

        MusicAppTextField(
            value = state.description,
            onValueChange = {},
            label = "Description",
            placeholder = "Enter playlist description",
        )
    }
}