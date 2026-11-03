package com.example.musicapp.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.navigation.NavRoutes
import com.example.musicapp.presentation.onboarding.components.OnboardingCard
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import com.example.musicapp.presentation.onboarding.mvi.OnboardingEffect
import com.example.musicapp.presentation.onboarding.mvi.OnboardingUIEvent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(OnboardingUIEvent.CheckAuthStatus)

        viewModel.effect.collectLatest {
            when (it) {
                is OnboardingEffect.NavigateToLogin -> {
                    navController.navigate(NavRoutes.LoginRoute)
                }

                is OnboardingEffect.NavigateToHome -> {
                    navController.navigate(NavRoutes.HomeRoute)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_welcome),
            contentDescription = stringResource(id = R.string.onboarding_background_image),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_girl),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.image_onboarding),
            )

            Spacer(modifier = Modifier.height((-16).dp))

            OnboardingCard(
                modifier = Modifier.fillMaxWidth(),
                onEvent = viewModel::onEvent
            )
        }
    }

    if (state.isLoading) {
        LoadingScreen()
    }
}
