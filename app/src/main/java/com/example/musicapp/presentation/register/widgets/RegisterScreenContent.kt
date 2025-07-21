package com.example.musicapp.presentation.register.widgets

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
import com.example.musicapp.presentation.common.components.MusicAppTextField
import com.example.musicapp.presentation.login.widgets.SocialCard
import com.example.musicapp.presentation.register.mvi.RegisterState
import com.example.musicapp.presentation.register.mvi.RegisterUIEvent
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.Shapes

@Composable
fun RegisterScreenContent(
    state: RegisterState,
    onEvent: (RegisterUIEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingLarge)
    ) {
        Image(
            modifier = Modifier
                .clickable { onEvent(RegisterUIEvent.OnBackClicked) },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(id = R.string.image_back)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Image(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.image_logo)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.register_your_account),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.size(16.dp))

        MusicAppTextField(
            value = state.name ?: "",
            onValueChange = { onEvent(RegisterUIEvent.OnNameUpdate(it)) },
            label = stringResource(id = R.string.name),
            placeholder = stringResource(id = R.string.name_placeholder),
            leadingIcon = painterResource(id = R.drawable.ic_mail),
            leadingDescription = stringResource(id = R.string.email_icon),
            isError = state.isNameValid
        )

        MusicAppTextField(
            value = state.email ?: "",
            onValueChange = { onEvent(RegisterUIEvent.OnEmailUpdate(it)) },
            label = stringResource(id = R.string.email),
            placeholder = stringResource(id = R.string.email_placeholder),
            leadingIcon = painterResource(id = R.drawable.ic_mail),
            leadingDescription = stringResource(id = R.string.email_icon),
            isError = state.isEmailValid
        )

        MusicAppTextField(
            value = state.password ?: "",
            onValueChange = { onEvent(RegisterUIEvent.OnPasswordUpdate(it)) },
            label = stringResource(id = R.string.password),
            placeholder = stringResource(id = R.string.password_placeholder),
            leadingIcon = painterResource(id = R.drawable.ic_mail),
            leadingDescription = stringResource(id = R.string.email_icon),
            trailingIcon = painterResource(id = R.drawable.ic_eye_off),
            isPasswordVisible = state.isPasswordVisible,
            trailingDescription = stringResource(id = R.string.icon_eye_off),
            onShowPasswordClicked = { onEvent(RegisterUIEvent.OnTogglePasswordVisibility) },
            isError = state.isPasswordValid
        )

        MusicAppTextField(
            value = state.confirmPassword ?: "",
            onValueChange = { onEvent(RegisterUIEvent.OnConfirmPasswordUpdate(it)) },
            label = stringResource(id = R.string.confirm_password),
            placeholder = stringResource(id = R.string.confirm_password),
            leadingIcon = painterResource(id = R.drawable.ic_mail),
            leadingDescription = stringResource(id = R.string.email_icon),
            trailingIcon = painterResource(id = R.drawable.ic_eye_off),
            isPasswordVisible = state.isConfirmPasswordVisible,
            trailingDescription = stringResource(id = R.string.icon_eye_off),
            onShowPasswordClicked = { onEvent(RegisterUIEvent.OnToggleConfirmPasswordVisibility) },
            isError = state.isConfirmPasswordValid
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
            onClick = { onEvent(RegisterUIEvent.OnRegisterClicked) },
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Box(modifier = Modifier.weight(1F))

        SocialCard(
            stringRes = stringResource(id = R.string.already_have_an_account),
            onClick = { onEvent(RegisterUIEvent.OnLoginClicked) },
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