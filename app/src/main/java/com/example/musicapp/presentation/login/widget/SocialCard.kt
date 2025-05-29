package com.example.musicapp.presentation.login.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicapp.R
import com.example.musicapp.presentation.login.components.SocialButton
import com.example.musicapp.presentation.login.components.SocialDivider
import com.example.musicapp.presentation.onboarding.components.HighlightedText
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun SocialCard(
    stringRes: String,
    onClick: () -> Unit,
    onFbClick: () -> Unit,
    onGoogleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = PaddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SocialDivider()

        Spacer(modifier = Modifier.size(PaddingLarge))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SocialButton(
                res = painterResource(R.drawable.ic_facebook),
                onClick = onFbClick
            )

            Spacer(modifier = Modifier.size(PaddingLarge))

            SocialButton(
                res = painterResource(R.drawable.ic_google),
                onClick = onGoogleClick
            )
        }

        Spacer(modifier = Modifier.size(PaddingLarge))

        HighlightedText(
            modifier = Modifier
                .clickable { onClick() },
            rawText = stringRes,
            spanStyle = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            ),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Preview
@Composable
private fun SocialCardPreview() {
    SocialCard(
        stringRes = stringResource(R.string.already_have_an_account),
        onClick = {},
        onFbClick = {},
        onGoogleClick = {},
    )
}