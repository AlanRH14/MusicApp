package com.example.musicapp.presentation.register.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.AuthenticationHeader
import com.example.musicapp.presentation.common.components.MusicAppButton
import com.example.musicapp.presentation.common.components.MusicAppTextField
import com.example.musicapp.presentation.login.components.LoginTopAppBar
import com.example.musicapp.presentation.login.widgets.SocialCard
import com.example.musicapp.presentation.register.mvi.RegisterState
import com.example.musicapp.presentation.register.mvi.RegisterUIEvent
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun RegisterScreenContent(
    state: RegisterState,
    onEvent: (RegisterUIEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            LoginTopAppBar { onEvent(RegisterUIEvent.OnBackClicked) }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(PaddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AuthenticationHeader(title = stringResource(id = R.string.register_your_account))

            MusicAppTextField(
                modifier = Modifier.padding(horizontal = PaddingLarge),
                value = state.name ?: "",
                onValueChange = { onEvent(RegisterUIEvent.OnNameUpdate(it)) },
                label = stringResource(id = R.string.name),
                placeholder = stringResource(id = R.string.name_placeholder),
                leadingIcon = painterResource(id = R.drawable.ic_mail),
                leadingDescription = stringResource(id = R.string.email_icon),
                isError = state.isNameValid
            )

            MusicAppTextField(
                modifier = Modifier.padding(horizontal = PaddingLarge),
                value = state.email ?: "",
                onValueChange = { onEvent(RegisterUIEvent.OnEmailUpdate(it)) },
                label = stringResource(id = R.string.email),
                placeholder = stringResource(id = R.string.email_placeholder),
                leadingIcon = painterResource(id = R.drawable.ic_mail),
                leadingDescription = stringResource(id = R.string.email_icon),
                isError = state.isEmailValid
            )

            MusicAppTextField(
                modifier = Modifier.padding(horizontal = PaddingLarge),
                value = state.password ?: "",
                onValueChange = { onEvent(RegisterUIEvent.OnPasswordUpdate(it)) },
                label = stringResource(id = R.string.password),
                placeholder = stringResource(id = R.string.password_placeholder),
                leadingIcon = painterResource(id = R.drawable.ic_mail),
                leadingDescription = stringResource(id = R.string.email_icon),
                trailingIcon = painterResource(id = R.drawable.ic_eye_off),
                isPasswordVisible = state.isPasswordVisible,
                isPasswordTextField = true,
                trailingDescription = stringResource(id = R.string.icon_eye_off),
                onTrailingIconClicked = { onEvent(RegisterUIEvent.OnTogglePasswordVisibility) },
                isError = state.isPasswordValid,
                visualTransformation = PasswordVisualTransformation(),
            )

            MusicAppTextField(
                modifier = Modifier.padding(horizontal = PaddingLarge),
                value = state.confirmPassword ?: "",
                onValueChange = { onEvent(RegisterUIEvent.OnConfirmPasswordUpdate(it)) },
                label = stringResource(id = R.string.confirm_password),
                placeholder = stringResource(id = R.string.confirm_password),
                leadingIcon = painterResource(id = R.drawable.ic_mail),
                leadingDescription = stringResource(id = R.string.email_icon),
                trailingIcon = painterResource(id = R.drawable.ic_eye_off),
                isPasswordVisible = state.isConfirmPasswordVisible,
                isPasswordTextField = true,
                trailingDescription = stringResource(id = R.string.icon_eye_off),
                onTrailingIconClicked = { onEvent(RegisterUIEvent.OnToggleConfirmPasswordVisibility) },
                isError = state.isConfirmPasswordValid,
                visualTransformation = PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.size(16.dp))

            MusicAppButton(
                modifier = Modifier.padding(horizontal = PaddingLarge),
                text = stringResource(id = R.string.register),
                onClick = { onEvent(RegisterUIEvent.OnRegisterClicked) }
            )

            Spacer(modifier = Modifier.height(PaddingLarge))

            SocialCard(
                stringRes = stringResource(id = R.string.already_have_an_account),
                onClick = { onEvent(RegisterUIEvent.OnLoginClicked) },
                onFbClick = {},
                onGoogleClick = {},
            )
        }
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
