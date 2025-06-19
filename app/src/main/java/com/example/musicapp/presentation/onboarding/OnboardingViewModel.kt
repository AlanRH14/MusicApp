package com.example.musicapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.ConstantsPreferences
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

    private val _event = MutableSharedFlow<OnboardingEffect>()
    val event = _event.asSharedFlow()

    fun onEvent(event: OnboardingUIEvent) {
        when (event) {
            is OnboardingUIEvent.OnGetStartedClicked -> navigationToLogin()
        }
    }

    fun isUserLoggedIn() {
        viewModelScope.launch {
            dataStoreHandle.readState(key = ConstantsPreferences.TokenPreferences)
                .collect { isUserLoggedIn ->
                    _state.update { it.copy(isUserLoggedIn = isUserLoggedIn.isNotEmpty()) }
                }
        }
    }

    private fun navigationToLogin() {
        viewModelScope.launch {
            _event.emit(OnboardingEffect.NavigationToLogin)
        }
    }
}