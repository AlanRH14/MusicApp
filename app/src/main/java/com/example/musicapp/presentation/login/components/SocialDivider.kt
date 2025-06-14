package com.example.musicapp.presentation.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musicapp.ui.theme.PaddingDefault
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun SocialDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingLarge),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1F),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            modifier = Modifier
                .padding(horizontal = PaddingDefault),
            text = "or continue with",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1F),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}