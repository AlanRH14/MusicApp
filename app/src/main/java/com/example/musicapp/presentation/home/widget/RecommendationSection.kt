package com.example.musicapp.presentation.home.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.domain.model.Song
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.presentation.home.component.AlbumItem
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingSmall
import com.example.musicapp.ui.theme.Shapes

@Composable
fun RecommendationSection(
    songs: List<Song>,
    onRecommendationClicked: (String) -> Unit
) {
    Text(
        text = stringResource(R.string.recommendation),
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.size(PaddingDefault))

    LazyRow(
        modifier = Modifier.padding(PaddingSmall)
    ) {
        items(items = songs, key = { it.id }) { song ->
            MusicAppImage(
                modifier = Modifier
                    .size(150.dp)
                    .clickable { onRecommendationClicked(song.id) }
                    .clip(Shapes.medium),
                pathImage = song.coverImage,
                imageDefault = R.drawable.ic_logo,
                placeHolder = {},
                contentDescription = "Song Recommendation Image",
            )
        }
    }
}