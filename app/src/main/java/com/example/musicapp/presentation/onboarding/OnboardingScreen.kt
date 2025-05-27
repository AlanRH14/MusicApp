package com.example.musicapp.presentation.onboarding

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.presentation.widgets.ErrorScreen
import com.example.musicapp.presentation.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest {
            when (it) {
                is OnboardingEvent.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.errorMessage ?: "Uknown",
            primaryButton = stringResource(R.string.retry),
            onPrimaryButtonClicked = {}
        )
    }
}