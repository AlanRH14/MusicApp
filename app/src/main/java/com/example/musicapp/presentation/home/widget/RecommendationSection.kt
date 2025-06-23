package com.example.musicapp.presentation.home.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.domain.model.Song
import com.example.musicapp.presentation.home.component.AlbumItem
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingSmall

@Composable
fun RecommendationSection(
    songs: List<Song>,
) {
    Text(
        text = "Recommendation",
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.size(PaddingDefault))

    LazyRow(
        modifier = Modifier.padding(PaddingSmall)
    ) {
        items(items = songs, key = { it.id }) { song ->

        }
    }
}