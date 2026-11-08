package com.example.musicapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.local.preferences.SessionManager
import com.example.musicapp.navigation.NavRoutes
import com.example.musicapp.presentation.splash.mvi.SplashState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SplashViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashState())
    val uiState: StateFlow<SplashState> = _uiState.asStateFlow()

    init {
        initializeAp()
    }

    private fun initializeAp() {
        viewModelScope.launch {
            try {
                val hasToken = sessionManager.token.value.isNotEmpty()
                val isLogged = sessionManager.sessionManager.value

                val destination = if (hasToken && isLogged) {
                    NavRoutes.HomeRoute
                } else {
                    NavRoutes.OnboardingRoute
                }
                delay(2000.milliseconds)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        destination = destination,
                    )
                }
            } catch (error: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        destination = NavRoutes.OnboardingRoute,
                        errorMessage = error.localizedMessage
                    )
                }
            }
        }
    }
}
