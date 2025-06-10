package com.example.musicapp.presentation.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    LaunchedEffect(key1 = viewModel) {
        viewModel.effect.collectLatest {
            when (it) {
                is LoginEffect.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }

                is LoginEffect.NavigationToRegister -> {
                    navController.navigate(RegisterRoute)
                }

                is LoginEffect.NavigationToBack -> {
                    navController.popBackStack()
                }

                is LoginEffect.NavigateToHome -> {
                    navController.navigate(HomeRoute)
                }
            }
        }
    }

    LoginScreenContent(
        state = state,
        onEvent = viewModel::onEvent
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
