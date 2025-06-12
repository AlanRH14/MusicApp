package com.example.musicapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.remote.repository.AuthenticationRepository
import com.example.musicapp.utils.emailFormatValid
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RegisterViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<RegisterEffect>()
    val event = _event.asSharedFlow()

    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.OnNameUpdate -> onNameUpdate(event.name)
            is RegisterUIEvent.OnEmailUpdate -> onEmailUpdate(event.email)
            is RegisterUIEvent.OnPasswordUpdate -> onPasswordUpdate(event.password)
            is RegisterUIEvent.OnConfirmPasswordUpdate -> onConfirmPasswordUpdate(event.confirmPassword)
            is RegisterUIEvent.OnTogglePasswordVisibility -> togglePasswordVisibility()
            is RegisterUIEvent.OnToggleConfirmPasswordVisibility -> toggleConfirmPasswordVisibility()
            is RegisterUIEvent.OnBackClicked -> navigateBack()
            is RegisterUIEvent.OnRegisterClicked -> register()
            is RegisterUIEvent.OnLoginClicked -> navigateBack()
        }
    }

    private fun onNameUpdate(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun onEmailUpdate(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun onPasswordUpdate(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun onConfirmPasswordUpdate(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    private fun togglePasswordVisibility() {
        _state.update { it.copy(isPasswordVisible = !_state.value.isPasswordVisible) }
    }

    private fun toggleConfirmPasswordVisibility() {
        _state.update { it.copy(isPasswordVisible = !_state.value.isConfirmPasswordVisible) }
    }

    private fun register() {
        viewModelScope.launch {
            if (invalidateTextFields()) return@launch

            _state.update { it.copy(isLoading = true) }
            val response = authenticationRepository.register()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _event.emit(RegisterEffect.NavigateToLogin)
        }
    }

    private fun invalidateTextFields(): Boolean {
        val isNameValid = _state.value.name.isNullOrEmpty()
        val isEmailValid = _state.value.email.isNullOrEmpty() || !_state.value.email.emailFormatValid()
        val isPasswordValid = _state.value.password.isNullOrEmpty()
        val isConfirmPasswordValid = _state.value.confirmPassword.isNullOrEmpty()

        _state.update {
            it.copy(
                isNameValid = isNameValid,
                isEmailValid = isEmailValid,
                isPasswordValid = isPasswordValid,
                isConfirmPasswordValid = isConfirmPasswordValid,
            )
        }

        return isNameValid || isEmailValid || isPasswordValid || isConfirmPasswordValid
    }
}