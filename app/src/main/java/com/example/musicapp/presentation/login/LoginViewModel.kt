package com.example.musicapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.LoginRequest
import com.example.musicapp.data.remote.repository.AuthenticationRepository
import com.example.musicapp.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.OnEmailChange -> updateEmail(event.email)
            is LoginUIEvent.OnPasswordChange -> updatePassword(event.password)
            is LoginUIEvent.IsPasswordVisibility -> togglePasswordVisibility()
            is LoginUIEvent.OnLoginClicked -> onLoginClicked()
            is LoginUIEvent.OnRegisterClicked -> navigateToRegister()
            is LoginUIEvent.OnForgotPasswordClicked -> handleForgotPassword()
            is LoginUIEvent.OnBackClicked -> navigateBack()
            is LoginUIEvent.OnDismissed -> dismissError()
        }
    }

    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun togglePasswordVisibility() {
        _state.update { it.copy(isPasswordVisibility = !it.isPasswordVisibility) }
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            if (validateInputs()) return@launch

            _state.update { it.copy(isLoading = true) }

            val response =
                repository.login(
                    LoginRequest(
                        email = state.value.email,
                        password = state.value.password
                    )
                )

            when (response) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false) }
                    _effect.emit(LoginEffect.NavigateToHome)
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.message
                        )
                    }
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val isEmailValid = _state.value.email.isNullOrEmpty()
        val isPasswordValid = _state.value.password.isNullOrEmpty()

        _state.update {
            it.copy(
                isEmailError = isPasswordValid,
                isPasswordError = isPasswordValid
            )
        }
        return isEmailValid && isPasswordValid
    }

    fun navigateToRegister() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.NavigationToRegister)
        }
    }

    fun handleForgotPassword() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.ShowErrorMessage("Forgot Password clicked"))
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.NavigationToBack)
        }
    }

    private fun dismissError() {
        _state.update { it.copy(errorMessage = null) }
    }
}