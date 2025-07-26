package com.example.musicapp.data.auth

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.musicapp.data.model.GoogleAccount
import com.example.musicapp.utils.Constants.GOOGLE_SERVICE_CLIENT_ID
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GoogleAuthUIProvider {
    suspend fun signIn(
        mContext: Context,
        credentialsManager: CredentialManager
    ): GoogleAccount = withContext(Dispatchers.IO){
        try {
            val credential = credentialsManager.getCredential(
                mContext,
                getCredentialsRequest()
            ).credential
            handleCredentials(credential = credential)
        } catch (e: Exception) {
            Log.d("LordMiau", "Error: ${e.message}")
            GoogleAccount()
        }
    }

    fun handleCredentials(credential: Credential): GoogleAccount {
        when {
            credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                return GoogleAccount(
                    token = googleIdTokenCredential.idToken,
                    displayName = googleIdTokenCredential.displayName,
                    profileImage = googleIdTokenCredential.profilePictureUri.toString()
                )
            }

            else -> {
                throw IllegalStateException("Invalid credential type")
            }
        }
    }

    private fun getCredentialsRequest(): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(
                GetSignInWithGoogleOption.Builder(
                    GOOGLE_SERVICE_CLIENT_ID
                ).build()
            ).build()
    }
}