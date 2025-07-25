package com.example.musicapp.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.musicapp.R
import com.example.musicapp.navigation.LoginRoute
import com.example.musicapp.presentation.register.widgets.RegisterScreenContent
import com.example.musicapp.presentation.common.widgets.ErrorScreen
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import com.example.musicapp.presentation.register.mvi.RegisterEffect
import com.example.musicapp.presentation.register.mvi.RegisterUIEvent
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
                is RegisterEffect.NavigateToLogin -> {
                    navController.popBackStack()
                }

                is RegisterEffect.NavigateToHome -> {
                    navController.navigate(LoginRoute)
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

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.error ?: stringResource(R.string.unknown),
            primaryButton = stringResource(R.string.retry),
            onPrimaryButtonClicked = {
                viewModel.onEvent(RegisterUIEvent.OnDismissed)
            }
        )
    }
}