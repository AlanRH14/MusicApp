package com.example.musicapp.presentation.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.presentation.widgets.ErrorScreen
import com.example.musicapp.presentation.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(true) {
        viewModel.event.collectLatest {
            when (it) {
                is HomeEvent.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        LoadingScreen(modifier = modifier)
    }

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            modifier = modifier,
            errorMessage = state.error ?: "",
            primaryButton = stringResource(R.string.retry),
            onPrimaryButtonClicked = {},
        )
    }
}