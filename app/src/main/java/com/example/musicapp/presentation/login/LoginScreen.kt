package com.example.musicapp.presentation.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.navigation.HomeRoute
import com.example.musicapp.navigation.RegisterRoute
import com.example.musicapp.presentation.login.widget.LoginScreenContent
import com.example.musicapp.presentation.widgets.ErrorScreen
import com.example.musicapp.presentation.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest {
            when (it) {
                is LoginEvent.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }

                is LoginEvent.NavigationToRegister -> {
                    navController.navigate(RegisterRoute)
                }

                is LoginEvent.NavigationToBack -> {
                    navController.popBackStack()
                }

                is LoginEvent.NavigateToHome -> {
                    navController.navigate(HomeRoute)
                }

                is LoginEvent.EmptyEmail -> {
                    isEmailError = true
                }

                is LoginEvent.EmptyPassword -> {
                    isPasswordError = true
                }
            }
        }
    }

    LoginScreenContent(
        email = state.email ?: "",
        password = state.password ?: "",
        isEmailError = isEmailError,
        isPasswordError = isPasswordError,
        onEmailChange = { viewModel.onEmailChanged(it) },
        onPasswordChange = { viewModel.onPasswordChanged(it) },
        onLoginClicked = {
            viewModel.onLoginClicked(
                email = state.email ?: "",
                password = state.password ?: ""
            )
        },
        onRegisterClicked = {
            viewModel.onRegisterClicked()
        },
        onForgotPasswordClicked = {
            viewModel.onForgotPasswordClicked()
        },
        onBackClicked = { viewModel.onBackClicked() }
    )

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.errorMessage ?: stringResource(R.string.unknown),
            primaryButton = stringResource(R.string.retry),
            onPrimaryButtonClicked = {}
        )
    }
}
