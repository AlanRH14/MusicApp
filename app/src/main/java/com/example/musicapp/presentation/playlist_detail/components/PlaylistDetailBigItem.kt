package com.example.musicapp.presentation.playlist_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun PlaylistDetailBigItem(
    title: String? = null,
    coverImage: String? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(!title.isNullOrEmpty()) {
            Text(
                modifier = Modifier.padding(PaddingDefault),
                text = title,
                style = MaterialTheme.typography.headlineMedium,
            )
        }

        if (!coverImage.isNullOrEmpty()) {
            MusicAppImage(
                modifier = Modifier.size(200.dp),
                pathImage = coverImage,
                imageDefault = R.drawable.ic_profile,
                placeHolder = {},
                contentDescription = "Playlist Image",
            )
        }
    }
}
