package com.example.musicapp.data.local.preferences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.musicapp.common.PreferencesKey
import com.example.musicapp.utils.PreferencesConstants

internal sealed class ConstantsPreferences<T> : PreferencesKey<T> {
    data object UserIsLoggedPreferences : ConstantsPreferences<Boolean>() {
        override val key: String = PreferencesConstants.USER_IS_LOGGED_KEY
        override val defaultValue: Boolean = false
        override val preferencesKey: Preferences.Key<Boolean> = booleanPreferencesKey(key)
    }

    data object AuthTokenPreferences: ConstantsPreferences<String>() {
        override val key: String = PreferencesConstants.TOKEN_PREFERENCES_KEY
        override val defaultValue: String = ""
        override val preferencesKey: Preferences.Key<String> = stringPreferencesKey(key)
    }
}
