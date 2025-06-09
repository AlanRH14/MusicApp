package com.example.musicapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RegisterViewModel : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<RegisterEvent>()
    val event = _event.asSharedFlow()

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onPasswordVisibilityChanged() {
        _state.update { it.copy(isPasswordVisible = !_state.value.isPasswordVisible) }
    }

    fun onRegisterClicked() {
        // TODO: Handle register click
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            _event.emit(RegisterEvent.NavigateToLogin)
        }
    }
}