package com.example.musicapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.remote.repository.HomeRepository
import com.example.musicapp.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    fun getHome() {
        viewModelScope.launch {
            val data = homeRepository.getHomeData()
            when (data) {
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            data = data.data,
                            isLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = data.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}