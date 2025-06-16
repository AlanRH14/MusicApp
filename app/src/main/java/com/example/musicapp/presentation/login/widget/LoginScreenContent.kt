package com.example.musicapp.presentation.login.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.presentation.common.components.MusicAppTextField
import com.example.musicapp.presentation.login.LoginState
import com.example.musicapp.presentation.login.LoginUIEvent
import com.example.musicapp.presentation.login.components.LoginCheckBox
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.Shapes

@Composable
fun LoginScreenContent(
    state: LoginState,
    onEvent: (LoginUIEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingLarge)
    ) {
        LoginHeader(onBackClicked = { onEvent(LoginUIEvent.OnBackClicked) })

        Spacer(modifier = Modifier.size(16.dp))

        MusicAppTextField(
            value = state.email ?: "",
            onValueChange = { onEvent(LoginUIEvent.OnEmailUpdate(it)) },
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email_placeholder),
            leadingIcon = painterResource(R.drawable.ic_mail),
            leadingDescription = stringResource(R.string.email_icon),
            isError = state.isEmailError,
        )

        Spacer(modifier = Modifier.size(8.dp))

        MusicAppTextField(
            value = state.password ?: "",
            onValueChange = { onEvent(LoginUIEvent.OnPasswordUpdate(it)) },
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.password_placeholder),
            leadingIcon = painterResource(R.drawable.ic_lock),
            leadingDescription = stringResource(R.string.icon_eye_off),
            trailingIcon = painterResource(R.drawable.ic_eye_off),
            isPasswordVisible = state.isPasswordVisibility,
            trailingDescription = stringResource(R.string.icon_eye_off),
            onShowPasswordClicked = { onEvent(LoginUIEvent.OnTogglePasswordVisibility) },
            isError = state.isPasswordError,
        )

        Spacer(modifier = Modifier.size(16.dp))

        LoginCheckBox(
            checked = state.rememberMeActive,
            onCheckedChange = { onEvent(LoginUIEvent.OnRememberMeActive) },
            text = stringResource(R.string.remember_me),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            modifier = Modifier
                .padding(horizontal = PaddingLarge)
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = Shapes.extraLarge,
                    ambientColor = MaterialTheme.colorScheme.primary,
                    spotColor = MaterialTheme.colorScheme.primary
                ),
            onClick = { onEvent(LoginUIEvent.OnLoginClicked) }
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
            onClick = { onEvent(LoginUIEvent.OnForgotPasswordClicked) }
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.weight(1F))

        SocialCard(
            stringRes = stringResource(R.string.do_not_have_an_account),
            onClick = { onEvent(LoginUIEvent.OnRegisterClicked) },
            onFbClick = {},
            onGoogleClick = {}
        )
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