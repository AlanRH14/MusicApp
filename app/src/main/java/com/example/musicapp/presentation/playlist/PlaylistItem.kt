package com.example.musicapp.presentation.playlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault

@Composable
fun PlaylistItem(
    playlist: Playlist
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingDefault)
    ) {
        MusicAppImage(
            pathImage = playlist.coverImage,
            imageDefault = R.drawable.ic_logo,
            placeHolder = {},
            contentDescription = stringResource(R.string.playlist_item_image)
        )
    }
}