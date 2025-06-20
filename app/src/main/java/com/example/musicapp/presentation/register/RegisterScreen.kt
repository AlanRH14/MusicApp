package com.example.musicapp.presentation.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.musicapp.R
import com.example.musicapp.navigation.HomeRoute
import com.example.musicapp.navigation.LoginRoute
import com.example.musicapp.presentation.register.widget.RegisterScreenContent
import com.example.musicapp.presentation.common.widgets.ErrorScreen
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import com.example.musicapp.presentation.register.mvi.RegisterEffect
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is RegisterEffect.ShowErrorMessage -> {
                    Toast.makeText(navController.context, event.message, Toast.LENGTH_SHORT).show()
                }

                is RegisterEffect.NavigateToLogin -> {
                    navController.popBackStack()
                }

                is RegisterEffect.NavigateToHome -> {
                    navController.navigate(HomeRoute) {
                        popUpTo(LoginRoute) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    RegisterScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
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