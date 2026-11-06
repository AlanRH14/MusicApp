package com.example.musicapp.data.local.preferences

import com.example.musicapp.domain.repository.DataStoreHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TokenProvider(
    dataStore: DataStoreHandle,
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {

    val token: StateFlow<String> = dataStore
        .readState(ConstantsPreferences.AuthTokenPreferences)
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = ""
        )
}
