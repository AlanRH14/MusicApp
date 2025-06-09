package com.example.musicapp.presentation.register.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import com.example.musicapp.presentation.login.widget.SocialCard
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.Shapes

@Composable
fun RegisterScreenContent(
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingLarge)
    ) {
        Image(
            modifier = Modifier
                .clickable { onLoginClicked() },
            painter = painterResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.image_back)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Image(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.image_logo)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.register_your_account),
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
            label = { Text(stringResource(R.string.name)) },
            placeholder = { Text("Enter your email") },
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

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = PaddingLarge)
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.email)) },
            placeholder = { stringResource(R.string.email_placeholder) },
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

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = PaddingLarge)
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.password)) },
            placeholder = { Text(stringResource(R.string.password_placeholder)) },
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

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = PaddingLarge)
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.confirm_password)) },
            placeholder = { Text(stringResource(R.string.confirm_password)) },
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

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = Shapes.extraLarge,
                    ambientColor = MaterialTheme.colorScheme.primary,
                    spotColor = MaterialTheme.colorScheme.primary
                ),
            onClick = {},
        ) {
            Text(
                text = stringResource(R.string.register),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Box(modifier = Modifier.weight(1F))

        SocialCard(
            stringRes = stringResource(R.string.already_have_an_account),
            onClick = onLoginClicked,
            onFbClick = {},
            onGoogleClick = {},
        )
    }
}

@Preview
@Composable
fun RegisterScreenContentPreview() {
    MusicAppTheme {
        RegisterScreenContent(
            onRegisterClicked = {},
            onLoginClicked = {}
        )
    }
}