package com.example.musicapp.presentation.login.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.LoginHeader
import com.example.musicapp.presentation.common.components.MusicAppButton
import com.example.musicapp.presentation.common.components.MusicAppTextField
import com.example.musicapp.presentation.login.mvi.LoginState
import com.example.musicapp.presentation.login.mvi.LoginUIEvent
import com.example.musicapp.presentation.login.components.LoginCheckBox
import com.example.musicapp.presentation.login.components.LoginTopAppBar
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingLarge

@Composable
fun LoginScreenContent(
    state: LoginState,
    onEvent: (LoginUIEvent) -> Unit
) {
    Scaffold(
        topBar = {
            LoginTopAppBar(onBackPress = { onEvent(LoginUIEvent.OnBackClicked) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(PaddingLarge)
        ) {
            LoginHeader()

            Spacer(modifier = Modifier.size(16.dp))

            MusicAppTextField(
                modifier = Modifier.padding(horizontal = PaddingLarge),
                value = state.email ?: "",
                onValueChange = { onEvent(LoginUIEvent.OnEmailUpdate(it)) },
                label = stringResource(id = R.string.email),
                placeholder = stringResource(id = R.string.email_placeholder),
                leadingIcon = painterResource(id = R.drawable.ic_mail),
                leadingDescription = stringResource(id = R.string.email_icon),
                isError = state.isEmailError,
            )

            Spacer(modifier = Modifier.size(8.dp))

            MusicAppTextField(
                modifier = Modifier.padding(horizontal = PaddingLarge),
                value = state.password ?: "",
                onValueChange = { onEvent(LoginUIEvent.OnPasswordUpdate(it)) },
                label = stringResource(id = R.string.password),
                placeholder = stringResource(id = R.string.password_placeholder),
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                leadingDescription = stringResource(id = R.string.icon_eye_off),
                trailingIcon = painterResource(id = R.drawable.ic_eye_off),
                isPasswordVisible = state.isPasswordVisible,
                isPasswordTextField = true,
                trailingDescription = stringResource(id = R.string.icon_eye_off),
                onTrailingIconClicked = { onEvent(LoginUIEvent.OnTogglePasswordVisibility) },
                isError = state.isPasswordError,
                visualTransformation = PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.size(16.dp))

            LoginCheckBox(
                checked = state.rememberMeActive,
                onCheckedChange = { onEvent(LoginUIEvent.OnRememberMeActive) },
                text = stringResource(id = R.string.remember_me),
            )

            Spacer(modifier = Modifier.size(16.dp))

            MusicAppButton(
                modifier = Modifier
                    .padding(horizontal = PaddingLarge),
                text = stringResource(id = R.string.login),
                onClick = { onEvent(LoginUIEvent.OnLoginClicked) }
            )

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingLarge),
                onClick = { onEvent(LoginUIEvent.OnForgotPasswordClicked) }
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.weight(1F))

            SocialCard(
                stringRes = stringResource(id = R.string.do_not_have_an_account),
                onClick = { onEvent(LoginUIEvent.OnRegisterClicked) },
                onFbClick = { onEvent(LoginUIEvent.OnFacebookSignInClicked) },
                onGoogleClick = { onEvent(LoginUIEvent.OngGoogleSignInClicked) }
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenContentPreview() {
    MusicAppTheme {
        LoginScreenContent(
            state = LoginState(),
            onEvent = {}
        )
    }
}
