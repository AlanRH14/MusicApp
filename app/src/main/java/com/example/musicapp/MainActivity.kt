package com.example.musicapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.musicapp.ui.theme.MusicAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var isSplashScreenVisible = true

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
                    Text(
                        modifier = Modifier.padding(innerPadding),
                        text = "Hello word!"
                    )
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            isSplashScreenVisible = false
        }
    }
}