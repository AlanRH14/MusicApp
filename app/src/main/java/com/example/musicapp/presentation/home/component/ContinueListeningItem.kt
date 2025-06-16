package com.example.musicapp.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.domain.model.Song
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingSmall
import com.example.musicapp.ui.theme.Shapes

@Composable
fun ContinueListeningItem(
    song: Song,
    onItemClicked: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(PaddingSmall)
            .fillMaxWidth()
            .padding(PaddingDefault)
            .clip(Shapes.small)
            .background(Color.Gray)
            .clickable { onItemClicked(song.id) }
    ) {
        MusicAppImage(
            modifier = Modifier
                .size(50.dp)
                .clip(Shapes.small),
            pathImage = song.coverImage,
            imageDefault = R.drawable.ic_logo,
            placeHolder = {},
            contentDescription = stringResource(R.string.continue_listening_image),
        )

        Text(
            text = song.title,
            maxLines = 1,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}