package com.example.musicapp.presentation.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppImage
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.PaddingSmall

@Composable
fun HomeHeader(
    userName: String,
    userImage: String,
    userImageDefault: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingLarge)
    ) {
        MusicAppImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            pathImage = userImage,
            imageDefault = userImageDefault,
            placeHolder = {},
            contentDescription = stringResource(R.string.user_image)
        )

        Spacer(modifier = Modifier.size(PaddingLarge))

        Column {
            Text(
                text = stringResource(R.string.welcome_back),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.size(PaddingSmall))

            Text(
                text = userName,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7F),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader(
        userName = "LordMiau",
        userImage = "",
        userImageDefault = R.drawable.ic_profile,)
}