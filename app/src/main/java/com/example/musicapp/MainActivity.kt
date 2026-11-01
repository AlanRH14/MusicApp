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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.navigation.AppNavGraph
import com.example.musicapp.navigation.NavRoutes
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.utils.QualifiersConstants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    private var isSplashScreenVisible = false
    private val ioDispatcher: CoroutineDispatcher by inject(qualifier = QualifiersConstants.DISPATCHERS_IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { !isSplashScreenVisible }

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

        lifecycleScope.launch(ioDispatcher) {
            delay(2000.milliseconds)
            isSplashScreenVisible = true
        }
    }
}
