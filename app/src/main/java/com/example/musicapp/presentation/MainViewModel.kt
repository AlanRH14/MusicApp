package com.example.musicapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.remote.repository.StatusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val repository: StatusRepository
) : ViewModel() {

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    init {
        getStatus()
    }

    private fun getStatus() {
        viewModelScope.launch {
            val result = repository.getStatus()
            _state.value = result
        }
    }
}