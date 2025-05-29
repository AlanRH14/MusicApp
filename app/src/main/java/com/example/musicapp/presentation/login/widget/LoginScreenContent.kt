package com.example.musicapp.presentation.login.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.presentation.onboarding.components.HighlightedText
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.Shapes

@Composable
fun LoginScreenContent(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onForgotPasswordClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingLarge)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.image_back),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = stringResource(R.string.image_logo),
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login_text),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = PaddingLarge)
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.email)) },
            placeholder = { Text(stringResource(R.string.email_placeholder)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_mail),
                    contentDescription = stringResource(R.string.email_icon),
                )
            },
            shape = Shapes.medium,
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.LightGray
            ),
        )

        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = PaddingLarge)
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.password)) },
            placeholder = { Text(stringResource(R.string.placeholder_password)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_mail),
                    contentDescription = stringResource(R.string.icon_eye_off)
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_eye_off),
                    contentDescription = stringResource(R.string.icon_eye_off)
                )
            },
            shape = Shapes.medium
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                modifier = Modifier.padding(start = PaddingLarge),
                checked = false,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Text(
                text = stringResource(R.string.remember_me),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            modifier = Modifier
                .padding(horizontal = PaddingLarge)
                .fillMaxWidth()
                .shadow(
                    8.dp,
                    Shapes.extraLarge,
                    ambientColor = MaterialTheme.colorScheme.primary,
                    spotColor = MaterialTheme.colorScheme.primary
                ),
            onClick = {}
        ) {
            Text(
                stringResource(R.string.login),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingLarge),
            onClick = {}
        ) {
            Text(
                text = "Forgot password?",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenContentPreview() {
    MusicAppTheme {
        LoginScreenContent(
            onLoginClicked = {},
            onRegisterClicked = {},
            onForgotPasswordClicked = {}
        )
    }
}