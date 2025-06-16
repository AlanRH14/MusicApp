package com.example.musicapp.presentation.common.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.musicapp.ui.theme.PaddingExtraLarge
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun ErrorScreen(
    errorMessage: String,
    primaryButton: String,
    secondaryButton: String? = null,
    onPrimaryButtonClicked: () -> Unit,
    onSecondaryButtonClicked: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.75F)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            modifier = Modifier.padding(horizontal = PaddingLarge),
            text = errorMessage,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            modifier = Modifier
                .padding(horizontal = PaddingExtraLarge)
                .width(200.dp),
            onClick = onPrimaryButtonClicked
        ) {
            Text(text = primaryButton)
        }

        Spacer(modifier = Modifier.size(16.dp))

        secondaryButton?.let {
            TextButton(onClick = { onSecondaryButtonClicked?.invoke() }) {
                Text(text = secondaryButton)
            }
        }
    }
}