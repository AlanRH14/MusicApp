package com.example.musicapp.data.local

import com.example.musicapp.common.PreferencesKey
import com.example.musicapp.utils.Constants.TOKEN_PREFERENCES_KEY
import com.example.musicapp.utils.Constants.USER_NAME_PREFERENCES_KEY

internal sealed class ConstantsPreferences<T: Any> : PreferencesKey<T> {

    data object TokenPreferences : ConstantsPreferences<String>() {
        override val key: String = TOKEN_PREFERENCES_KEY
        override val defaultValue: String = ""
    }

    data object UserPreferences : ConstantsPreferences<String>() {
        override val key: String = USER_NAME_PREFERENCES_KEY
        override val defaultValue: String = ""
    }
}