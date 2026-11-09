package com.example.musicapp.data.local.preferences

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import java.io.InputStream
import java.io.OutputStream


object EncryptedDataStoreSerializer : Serializer<Preferences> {

    override suspend fun readFrom(input: InputStream): Preferences {
        return try {
            val decryptedBytes = CryptoManager.decrypt(input)
            defaultValue
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: Preferences,
        output: OutputStream
    ) {
        CryptoManager.encrypt(t.toString().toByteArray(), output)
    }

    override val defaultValue: Preferences
        get() = emptyPreferences()
}