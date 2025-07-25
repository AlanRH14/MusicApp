package com.example.musicapp.data.auth

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.musicapp.data.model.GoogleAccount
import com.example.musicapp.utils.Constants.GOOGLE_SERVICE_CLIENT_ID
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class GoogleAuthUIProvider {
    suspend fun signIn(
        mContext: Context,
        credentialsManager: CredentialManager
    ): GoogleAccount {
        val credential = credentialsManager.getCredential(
            mContext,
            getCredentialsRequest()
        ).credential

        return handleCredentials(credential = credential)
    }

    fun handleCredentials(credential: Credential): GoogleAccount {
        when {
            credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                val googleIDTokenCredential = credential as GoogleIdTokenCredential
                return GoogleAccount(
                    token = googleIDTokenCredential.idToken,
                    displayName = googleIDTokenCredential.displayName,
                    profileImage = googleIDTokenCredential.profilePictureUri.toString()
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