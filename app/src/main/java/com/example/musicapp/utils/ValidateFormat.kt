package com.example.musicapp.utils

import android.util.Patterns.EMAIL_ADDRESS
import kotlin.math.pow
import kotlin.math.round

object ValidateFormat {
    fun String?.emailFormatValid(): Boolean =
        EMAIL_ADDRESS.matcher(this ?: "").matches()

    fun String.passwordFormatValid(): Boolean =
        true

    fun Long?.toDecimalValue(decimalPlaces: Int = 2): String {
        val factor = 10.0.pow(decimalPlaces.toDouble())
        return (round((this?.toDouble() ?: 0.0) * factor) / factor)
            .toString()
    }
}
