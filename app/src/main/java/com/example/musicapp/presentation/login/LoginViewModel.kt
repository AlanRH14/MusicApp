package com.example.musicapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.utils.Resource
import com.example.musicapp.utils.emailFormatValid
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.OnEmailUpdate -> onEmailUpdate(event.email)
            is LoginUIEvent.OnPasswordUpdate -> onPasswordUpdate(event.password)
            is LoginUIEvent.OnTogglePasswordVisibility -> togglePasswordVisibility()
            is LoginUIEvent.OnLoginClicked -> login()
            is LoginUIEvent.OnRememberMeActive -> checkUpdate()
            is LoginUIEvent.OnRegisterClicked -> navigateToRegister()
            is LoginUIEvent.OnForgotPasswordClicked -> handleForgotPassword()
            is LoginUIEvent.OnBackClicked -> navigateBack()
            is LoginUIEvent.OnDismissed -> dismissError()
        }
    }

    private fun onEmailUpdate(email: String) {
        _state.update {
            it.copy(
                email = email,
                isEmailError = email.isEmpty()
            )
        }
    }

    private fun onPasswordUpdate(password: String) {
        _state.update {
            it.copy(
                password = password,
                isPasswordError = password.isEmpty()
            )
        }
    }

    private fun togglePasswordVisibility() {
        _state.update { it.copy(isPasswordVisibility = !it.isPasswordVisibility) }
    }

    private fun login() {
        viewModelScope.launch {
            if (invalidateInputs()) return@launch

            val response =
                repository.login(
                    LoginRequest(
                        email = state.value.email,
                        password = state.value.password
                    )
                )

            when (response) {
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false) }
                    _effect.emit(LoginEffect.NavigateToHome)
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.message,
                        )
                    }
                }
            }
        }
    }

    private fun invalidateInputs(): Boolean {
        val isEmailValid = _state.value.email.isNullOrEmpty() || !_state.value.email.emailFormatValid()
        val isPasswordValid = _state.value.password.isNullOrEmpty()

        _state.update {
            it.copy(
                isEmailError = isEmailValid,
                isPasswordError = isPasswordValid
            )
        }

        return isEmailValid || isPasswordValid
    }

    private fun navigateToRegister() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.NavigationToRegister)
        }
    }

    private fun checkUpdate() {
        _state.update { it.copy(rememberMeActive = !_state.value.rememberMeActive) }
    }

    private fun handleForgotPassword() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.ShowErrorMessage("Forgot Password clicked"))
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.NavigationToBack)
        }
    }

    private fun dismissError() {
        _state.update { it.copy(errorMessage = null) }
    }
}