package com.example.musicapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.remote.repository.StatusRepositoryImpl
import com.example.musicapp.utils.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: StatusRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    init {
        getStatus()
    }

    private fun getStatus() {
        viewModelScope.launch {
            repository.getStatus().collect { status ->
                when (status) {
                    is ResponseState.Success -> {
                        _state.value = status.data
                    }

                    else -> Unit
                }
            }
        }
    }
}