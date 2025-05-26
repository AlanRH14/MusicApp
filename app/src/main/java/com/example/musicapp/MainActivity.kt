package com.example.musicapp

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.musicapp.presentation.MainViewModel
import com.example.musicapp.ui.theme.MusicAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var isSplashScreenVisible = true
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { isSplashScreenVisible }
            setOnExitAnimationListener { splashProvider ->
                val zoomX = ObjectAnimator.ofFloat(
                    splashProvider.iconView,
                    "scaleX",
                    0.4F,
                    0F
                )

                val zoomY = ObjectAnimator.ofFloat(
                    splashProvider.iconView,
                    "scaleY",
                    0.4F,
                    0F
                )

                zoomX.apply {
                    duration = 300
                    doOnEnd {
                        splashProvider.remove()
                    }
                    start()
                }
                zoomY.apply {
                    duration = 300
                    doOnEnd {
                        splashProvider.remove()
                    }
                    start()
                }
            }
        }
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text(text = "Hello word!")

                        Text(
                            text = "This is the main content of the app",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            isSplashScreenVisible = false
        }
    }
}