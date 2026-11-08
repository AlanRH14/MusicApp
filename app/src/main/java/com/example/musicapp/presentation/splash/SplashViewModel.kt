package com.example.musicapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.local.preferences.SessionManager
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.navigation.NavRoutes
import com.example.musicapp.presentation.splash.mvi.SplashState
import com.example.musicapp.utils.constants.PreferencesConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SplashViewModel(
    private val dataStore: DataStoreHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashState())
    val uiState: StateFlow<SplashState> = _uiState.asStateFlow()

    init {
        initializeAp()
    }

    private fun initializeAp() {
        viewModelScope.launch {
            try {
                val hasToken = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
                val isLogged = dataStore.readState(ConstantsPreferences.UserIsLoggedPreferences).first()

                val destination = if (hasToken.isNotEmpty() && isLogged) {
                    NavRoutes.HomeRoute
                } else {
                    NavRoutes.OnboardingRoute
                }

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
