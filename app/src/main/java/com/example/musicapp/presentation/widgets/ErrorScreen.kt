package com.example.musicapp.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
    primaryButton: String,
    secondaryButton: String? = null,
    onPrimaryButtonClicked: () -> Unit,
    onSecondaryButtonClicked: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = errorMessage)
        Button(onClick = onPrimaryButtonClicked) {
            Text(text = primaryButton)
        }

        secondaryButton?.let {
            Button(onClick = { onSecondaryButtonClicked?.invoke() }) {
                Text(text = secondaryButton)
            }
        }
    }
}