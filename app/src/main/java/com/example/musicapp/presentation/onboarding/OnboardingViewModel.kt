package com.example.musicapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.ConstantsPreferences
import com.example.musicapp.domain.repository.DataStoreHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val dataStoreHandle: DataStoreHandle
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<OnboardingEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: OnboardingUIEvent) {
        when (event) {
            is OnboardingUIEvent.CheckAuthStatus -> isUserLoggedIn()
            is OnboardingUIEvent.OnGetStartedClicked -> navigationToLogin()
        }
    }

    private fun isUserLoggedIn() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            dataStoreHandle.readState(key = ConstantsPreferences.TokenPreferences)
                .collect { token ->
                    val isLoggedIn = token.isNotEmpty()

                    _state.update {
                        it.copy(
                            isLoading = false,
                            isUserLoggedIn = isLoggedIn
                        )
                    }

                    if (isLoggedIn) {
                        _effect.emit(OnboardingEffect.NavigateToHome)
                    }
                }
        }
    }

    private fun navigationToLogin() {
        viewModelScope.launch {
            _effect.emit(OnboardingEffect.NavigationToLogin)
        }
    }
}