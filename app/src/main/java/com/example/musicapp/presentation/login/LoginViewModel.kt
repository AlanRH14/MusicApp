package com.example.musicapp.presentation.login

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.common.Resource
import com.example.musicapp.data.auth.GoogleAuthUIProvider
import com.example.musicapp.presentation.login.mvi.LoginEffect
import com.example.musicapp.presentation.login.mvi.LoginState
import com.example.musicapp.presentation.login.mvi.LoginUIEvent
import com.example.musicapp.utils.ValidateFormat.emailFormatValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthenticationRepository,
    private val dataStoreHandle: DataStoreHandle,
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
            is LoginUIEvent.OnGoogleSignInClicked -> signInGoogle(event.mContext)
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
        _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    private fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            if (invalidateInputs()) return@launch

            val response = repository.login(
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
                    if (response.data.token.isNotEmpty() && _state.value.rememberMeActive) {
                        dataStoreHandle.saveState(
                            key = ConstantsPreferences.UserIsLoggedPreferences,
                            value = true
                        )
                    }
                    _effect.emit(LoginEffect.NavigateToHome)
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = response.message,
                        )
                    }
                }
            }
        }
    }

    private fun invalidateInputs(): Boolean {
        val isEmailValid =
            _state.value.email.isNullOrEmpty() || !_state.value.email.emailFormatValid()
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
            _effect.emit(LoginEffect.NavigateToRegister)
        }
    }

    private fun checkUpdate() {
        _state.update { it.copy(rememberMeActive = !_state.value.rememberMeActive) }
    }

    private fun handleForgotPassword() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.ShowMessage("Forgot Password clicked"))
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(LoginEffect.NavigateToBack)
        }
    }

    private fun dismissError() {
        _state.update { it.copy(error = null) }
    }
}