package com.example.musicapp.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicapp.R

@Composable
fun SocialButton(
    res: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(
                1.dp,
                MaterialTheme.colorScheme.surface,
                CircleShape
            )
            .size(48.dp)
            .clickable { onClick() }
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit,
            painter = res,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
fun SocialButtonPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SocialButton(
            res = painterResource(R.drawable.ic_facebook),
            onClick = {},
        )

        SocialButton(
            res = painterResource(R.drawable.ic_google),
            onClick = {},
        )
    }
}