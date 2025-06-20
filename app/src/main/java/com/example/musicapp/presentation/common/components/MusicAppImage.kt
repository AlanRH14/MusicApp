package com.example.musicapp.presentation.common.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.error

@Composable
fun MusicAppImage(
    modifier: Modifier = Modifier,
    pathImage: String,
    imageDefault: Int,
    placeHolder: @Composable () -> Unit,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(pathImage)
        .error(imageDefault)
        .build()

    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageRequest,
        loading = { placeHolder() },
        onError = { Log.e("LordMiau", "Error load image ${it.result.throwable.message}")},
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}