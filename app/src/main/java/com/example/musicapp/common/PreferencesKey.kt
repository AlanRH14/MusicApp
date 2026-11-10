package com.example.musicapp.common

import androidx.datastore.preferences.core.Preferences

interface PreferencesKey<T> {
    val key: String
    val defaultValue: T
    val preferencesKey: Preferences.Key<T>

    fun encrypt(value: T): T = value
    fun decrypt(value: T): T = value
}
