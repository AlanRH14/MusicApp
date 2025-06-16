package com.example.musicapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil3.request.ImageRequest

@Composable
fun MusicAppImage(
    modifier: Modifier = Modifier,
    pathImage: String,
    image: Painter? = null,
    contentDescription: String?= null,
    placeHolder: Painter
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(image ?: pathImage
    )
}