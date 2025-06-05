package com.example.musicapp.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.ui.theme.PaddingExtraLarge
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.Shapes

@Composable
fun OnboardingCard(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(PaddingExtraLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HighlightedText(
            modifier = Modifier
                .padding(PaddingLarge)
                .fillMaxWidth(),
            rawText = stringResource(R.string.onboarding_text),
            spanStyle = SpanStyle(color = MaterialTheme.colorScheme.primary),
            textStyle = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .padding(vertical = PaddingLarge)
                .width(70.dp)
                .height(8.dp)
                .clip(Shapes.small)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
                    .background(MaterialTheme.colorScheme.surface)
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick
        ) {
            Text(
                text = stringResource(R.string.get_started),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}