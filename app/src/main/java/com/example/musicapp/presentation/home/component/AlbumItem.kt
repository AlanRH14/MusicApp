package com.example.musicapp.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.domain.model.Album
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.Shapes

@Composable
fun AlbumItem(
    album: Album,
    onAlbumClicked: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(150.dp)
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
    }
}