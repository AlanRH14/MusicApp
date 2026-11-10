package com.example.musicapp.data.local.preferences

import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.SessionManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SessionManagerImpl(
    private val dataStore: DataStoreHandle,
    ioDispatcher: CoroutineDispatcher,
) : SessionManager {

    private val scope = CoroutineScope(ioDispatcher + SupervisorJob())

    private val tokenState = MutableStateFlow("")

    init {
        scope.launch {
            dataStore.readState(ConstantsPreferences.AuthTokenPreferences).collect { newToken ->
                tokenState.value = newToken
            }
        }
    }

    override fun getToken(): String = tokenState.value
}
