package com.example.musicapp.presentation.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.musicapp.R
import com.example.musicapp.presentation.register.widget.RegisterScreenContent
import com.example.musicapp.presentation.widgets.ErrorScreen
import com.example.musicapp.presentation.widgets.LoadingScreen
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
            }
        }
    }

    RegisterScreenContent(
        state = state,
        onEvent = {},
    )

    if (state.loading) {
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