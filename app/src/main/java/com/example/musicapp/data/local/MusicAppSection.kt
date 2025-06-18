package com.example.musicapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.musicapp.utils.Constants.ONBOARDING_PREFERENCES_KEY
import com.example.musicapp.utils.Constants.PREFERENCES_NAME

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class MusicAppSection(private val mContext: Context) {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = ONBOARDING_PREFERENCES_KEY)
    }

}