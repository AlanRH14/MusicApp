package com.example.musicapp.data.local.preferences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.musicapp.common.PreferencesKey
import com.example.musicapp.utils.PreferencesConstants.TOKEN_PREFERENCES_KEY
import com.example.musicapp.utils.PreferencesConstants.USER_NAME_PREFERENCES_KEY

internal sealed class ConstantsPreferences<T> : PreferencesKey<T> {

    data object UserIsLoggedPreferences : ConstantsPreferences<Boolean>() {
        override val key: String = TOKEN_PREFERENCES_KEY
        override val defaultValue: Boolean = false
        override val preferencesKey: Preferences.Key<Boolean> = booleanPreferencesKey(key)
    }

    data object UserPreferences : ConstantsPreferences<String>() {
        override val key: String = USER_NAME_PREFERENCES_KEY
        override val defaultValue: String = ""
        override val preferencesKey: Preferences.Key<String> = stringPreferencesKey(key)
    }
}