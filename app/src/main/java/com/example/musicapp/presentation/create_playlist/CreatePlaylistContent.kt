package com.example.musicapp.presentation.create_playlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            value = state.description,
            onValueChange = {},
            label = "Description",
            placeholder = "Enter playlist description",
        )
    }
}