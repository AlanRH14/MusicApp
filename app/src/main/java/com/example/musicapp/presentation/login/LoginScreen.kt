package com.example.musicapp.presentation.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
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

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest {
            when (it) {
                is LoginEvent.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }

                is LoginEvent.NavigationToRegister -> {
                    navController.navigate(RegisterRoute)
                }
            }
        }
    }

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

    LoginScreenContent(
        onLoginClicked = {},
        onRegisterClicked = {
            viewModel.onRegisterClicked()
        },
        onForgotPasswordClicked = {
            viewModel.onForgotPasswordClicked()
        }
    )
}
