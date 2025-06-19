package com.example.musicapp.presentation.home.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.domain.model.Song
import com.example.musicapp.presentation.home.component.ContinueListeningItem
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.utils.Constants.GRID_CELLS_SIZE

@Composable
fun ContinueListeningSection(
    songs: List<Song>,
    onItemClicked: (String) -> Unit
) {
    Text(
        text = "Continue Listening",
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.size(PaddingDefault))

    LazyVerticalGrid(columns = GridCells.Fixed(GRID_CELLS_SIZE)) {
        items(songs, key = { it.id }) { song ->
            ContinueListeningItem(
                song = song,
                onItemClicked = onItemClicked
            )
        }
    }
}