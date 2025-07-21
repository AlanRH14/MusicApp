package com.example.musicapp.presentation.playlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            modifier = Modifier.size(60.dp),
            pathImage = playlist.coverImage,
            imageDefault = R.drawable.ic_logo,
            placeHolder = {},
            contentDescription = stringResource(R.string.playlist_item_image)
        )

        Column {
            Text(
                modifier = Modifier.padding(PaddingDefault),
                text = playlist.name
            )
            Text(
                modifier = Modifier.padding(start = PaddingDefault),
                text = playlist.description
            )
        }
    }
}