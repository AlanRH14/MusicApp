package com.example.musicapp.presentation.login

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
class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onPasswordVisibilityChanged() {
        _state.update { it.copy(isPasswordVisibility = !_state.value.isPasswordVisibility) }
    }

    fun onLoginClicked() {}

    fun onRegisterClicked() {
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigationToRegister)
        }
    }

    fun onForgotPasswordClicked() {
        viewModelScope.launch {
            _event.emit(LoginEvent.ShowErrorMessage("Forgot Password clicked"))
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigationToBack)
        }
    }
}