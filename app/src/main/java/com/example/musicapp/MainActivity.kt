package com.example.musicapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.navigation.AppNavGraph
import com.example.musicapp.navigation.NavRoutes
import com.example.musicapp.presentation.splash.SplashViewModel
import com.example.musicapp.ui.theme.MusicAppTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var isReady = false

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.uiState.collectLatest { state ->
                    isReady = !state.isLoading
                }
            }
        }

        splashScreen.setKeepOnScreenCondition { !isReady }

        splashScreen.setOnExitAnimationListener { splashProvider ->
            val scaleX = ObjectAnimator.ofFloat(
                splashProvider.iconView,
                View.SCALE_X,
                0.4F,
                0F
            )

            val scaleY = ObjectAnimator.ofFloat(
                splashProvider.iconView,
                View.SCALE_Y,
                0.4F,
                0F
            )

            AnimatorSet().apply {
                playTogether(scaleX, scaleY)
                duration = 300
                doOnEnd { splashProvider.remove() }
                start()
            }
        }

        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AppNavGraph(
                            navController = navController,
                            startDestination = NavRoutes.OnboardingRoute,
                        )
                    }
                }
            }
        }
    }
}
