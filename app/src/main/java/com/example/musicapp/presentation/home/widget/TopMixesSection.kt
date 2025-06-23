package com.example.musicapp.presentation.home.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.domain.model.Album
import com.example.musicapp.presentation.home.component.AlbumItem
import com.example.musicapp.ui.theme.PaddingDefault

@Composable
fun TopMixesSection(
    albums: List<Album>,
    onAlbumClicked: (String) -> Unit
) {

    Text(
        text = "Top Mixes",
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.size(PaddingDefault))

    LazyRow {
        items(albums, key = { it.id }) { album ->
            AlbumItem(
                album = album,
                onAlbumClicked = onAlbumClicked
            )
        }
    }
}