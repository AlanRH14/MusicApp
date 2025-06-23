package com.example.musicapp.data.local.preferences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.example.musicapp.common.PreferencesKey
import com.example.musicapp.utils.PreferencesConstants.TOKEN_PREFERENCES_KEY

internal sealed class ConstantsPreferences<T> : PreferencesKey<T> {

    data object UserIsLoggedPreferences : ConstantsPreferences<Boolean>() {
        override val key: String = TOKEN_PREFERENCES_KEY
        override val defaultValue: Boolean = false
        override val preferencesKey: Preferences.Key<Boolean> = booleanPreferencesKey(key)
    }
}