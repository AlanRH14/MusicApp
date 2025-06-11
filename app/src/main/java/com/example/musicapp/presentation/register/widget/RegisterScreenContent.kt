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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.presentation.components.MusicAppTextField
import com.example.musicapp.presentation.login.widget.SocialCard
import com.example.musicapp.presentation.register.RegisterState
import com.example.musicapp.presentation.register.RegisterUIEffect
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.Shapes

@Composable
fun RegisterScreenContent(
    state: RegisterState,
    onEvent: (RegisterUIEffect) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingLarge)
    ) {
        Image(
            modifier = Modifier
                .clickable { onEvent(RegisterUIEffect.OnBackClicked) },
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

        MusicAppTextField(
            value = state.name ?: "",
            onValueChange = { onEvent(RegisterUIEffect.OnNameUpdate(it)) },
            label = stringResource(R.string.name),
            placeholder = stringResource(R.string.name_placeholder),
            leadingIcon = painterResource(R.drawable.ic_mail),
            leadingDescription = stringResource(R.string.email_icon),
        )

        MusicAppTextField(
            value = state.email ?: "",
            onValueChange = { onEvent(RegisterUIEffect.OnEmailUpdate(it)) },
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email_placeholder),
            leadingIcon = painterResource(R.drawable.ic_mail),
            leadingDescription = stringResource(R.string.email_icon),
        )

        MusicAppTextField(
            value = state.password ?: "",
            onValueChange = { onEvent(RegisterUIEffect.OnPasswordUpdate(it)) },
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.password_placeholder),
            leadingIcon = painterResource(R.drawable.ic_mail),
            leadingDescription = stringResource(R.string.email_icon),
            trailingIcon = painterResource(R.drawable.ic_eye_off),
            trailingDescription = stringResource(R.string.icon_eye_off),
        )

        MusicAppTextField(
            value = state.confirmPassword ?: "",
            onValueChange = { onEvent(RegisterUIEffect.OnConfirmPasswordUpdate(it)) },
            label = stringResource(R.string.confirm_password),
            placeholder = stringResource(R.string.confirm_password),
            leadingIcon = painterResource(R.drawable.ic_mail),
            leadingDescription = stringResource(R.string.email_icon),
            trailingIcon = painterResource(R.drawable.ic_eye_off),
            trailingDescription = stringResource(R.string.icon_eye_off),
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
            onClick = { onEvent(RegisterUIEffect.OnRegisterClicked) },
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
            onClick = { onEvent(RegisterUIEffect.OnLoginClicked) },
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
            state = RegisterState(),
            onEvent = {},
        )
    }
}