package com.example.musicapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.local.preferences.SessionManager
import com.example.musicapp.presentation.splash.mvi.SplashState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SplashViewModel(
    private val sessionManager: SessionManager,
    private val
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashState())
    val uiState: StateFlow<SplashState> = _uiState.asStateFlow()

    init {
        initializeAp()
    }

    private fun initializeAp() {
        viewModelScope.launch {
            try {
                val
                delay(2000.milliseconds)
                _uiState.value = _uiState.value.copy(isLoading = false)
            } catch (error: Exception) {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.localizedMessage
                    )
            }
        }
    }
}
