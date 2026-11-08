package com.example.musicapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.navigation.AppNavGraph
import com.example.musicapp.presentation.splash.SplashViewModel
import com.example.musicapp.ui.theme.MusicAppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            splashViewModel.uiState.value.isLoading
        }

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
                val uiState by splashViewModel.uiState.collectAsStateWithLifecycle()

                if (!uiState.isLoading) {
                    val navController = rememberNavController()
                    AppNavGraph(
                        navController = navController,
                        startDestination = uiState.destination,
                    )
                }
            }
        }
    }
}
