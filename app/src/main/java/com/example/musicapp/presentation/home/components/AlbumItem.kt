package com.example.musicapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.domain.model.Album
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingSmall
import com.example.musicapp.ui.theme.Shapes

@Composable
fun AlbumItem(
    album: Album,
    onAlbumClicked: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(PaddingSmall)
            .clip(Shapes.small)
            .background(Color.Gray)
            .clickable { onAlbumClicked(album.id) }
    ) {
        MusicAppImage(
            modifier = Modifier
                .size(150.dp)
                .clip(Shapes.small),
            pathImage = album.coverImage,
            imageDefault = R.drawable.ic_logo,
            placeHolder = {},
            contentDescription = stringResource(R.string.album_item_image)
        )

        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(PaddingDefault),
            text = album.title,
            maxLines = 1,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )

        Box(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(createRandomColor())
        )
    }
}

private fun createRandomColor(): Color {
    val red = (0..255).random()
    val blue = (0..255).random()
    val green = (0..255).random()

    return Color(red = red, blue = blue, green = green)
}