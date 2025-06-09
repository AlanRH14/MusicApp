package com.example.musicapp.presentation.onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.navigation.LoginRoute
import com.example.musicapp.navigation.OnboardingRoute
import com.example.musicapp.presentation.onboarding.components.OnboardingCard
import com.example.musicapp.presentation.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var cardHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest {
            when (it) {
                is OnboardingEvent.ShowErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }

                is OnboardingEvent.NavigationToLogin -> {
                    navController.navigate(LoginRoute)
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
            painter = painterResource(R.drawable.ic_welcome),
            contentDescription = stringResource(R.string.onboarding_background_image),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-cardHeight.minus(20.dp))),
                painter = painterResource(R.drawable.ic_girl),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.image_onboarding),
            )
            OnboardingCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned {
                        val heightPX = it.size.height
                        cardHeight = with(density) {
                            heightPX.toDp()
                        }
                    },
                onClick = {
                    viewModel.onGetStartedClicked()
                }
            )
        }
    }

    if (state.isLoading) {
        LoadingScreen()
    }
}