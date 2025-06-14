package com.example.musicapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.remote.repository.StatusRepository
import com.example.musicapp.utils.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    val repository: StatusRepository
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