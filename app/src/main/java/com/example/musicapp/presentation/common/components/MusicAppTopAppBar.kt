package com.example.musicapp.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.ui.theme.PaddingLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicAppTopAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.welcome_back),
    subtitle: String = "",
    userImage: String,
    userImageDefault: Int,
    onPlaylistButtonClicked: () -> Unit
) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Column(modifier = Modifier.padding(PaddingLarge)) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
                )

                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7F),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium,

                        )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            MusicAppImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp),
                pathImage = userImage,
                imageDefault = userImageDefault,
                placeHolder = {},
                contentDescription = stringResource(id = R.string.user_image)
            )
        },
        actions = {
            IconButton(onClick = onPlaylistButtonClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                    contentDescription = stringResource(id = R.string.playlist_add),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}

@Preview
@Composable
fun MusicAppTopAppBarPreview() {
    MusicAppTopAppBar(
        subtitle = "LordMiau",
        userImage = "",
        userImageDefault = R.drawable.ic_profile,
        onPlaylistButtonClicked = {}
    )
}

@Preview
@Composable
fun MusicAppTopAppBarNoSubtitlePreview() {
    MusicAppTopAppBar(
        title = "LordMiau",
        userImage = "",
        userImageDefault = R.drawable.ic_profile,
        onPlaylistButtonClicked = {}
    )
}
