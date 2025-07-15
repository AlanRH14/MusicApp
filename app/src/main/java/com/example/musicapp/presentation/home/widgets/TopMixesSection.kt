package com.example.musicapp.presentation.home.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R
import com.example.musicapp.domain.model.Album
import com.example.musicapp.presentation.home.components.AlbumItem
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingSmall

@Composable
fun TopMixesSection(
    albums: List<Album>,
    onAlbumClicked: (String) -> Unit
) {

    Text(
        text = stringResource(R.string.top_mixes),
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.size(PaddingDefault))

    LazyRow(
        modifier = Modifier.padding(PaddingSmall)
    ) {
        items(albums, key = { it.id }) { album ->
            AlbumItem(
                album = album,
                onAlbumClicked = onAlbumClicked
            )
        }
    }
}