package com.example.musicapp.presentation.play_song.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.musicapp.ui.theme.PaddingLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistBottomSheet(
    shouldShowSheet: Boolean,
    sheetState: SheetState,
) {
    var rememberTest by remember { mutableStateOf(shouldShowSheet) }
    ModalBottomSheet(
        onDismissRequest = { rememberTest = false },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingLarge)
        ) {

        }
    }
}