package com.example.musicapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.example.musicapp.ui.theme.PaddingLarge
import com.example.musicapp.ui.theme.Shapes

@Composable
fun MusicAppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: Painter,
    leadingDescription: String,
    trailingIcon: Painter? = null,
    trailingDescription: String? = null,
    isError: Boolean = false,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
        focusedIndicatorColor = Color.LightGray
    )
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = PaddingLarge)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(
                painter = leadingIcon,
                contentDescription = leadingDescription,
            )
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(placeholder)
            }
        },
        trailingIcon = {
            trailingIcon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = trailingDescription
                )
            }
        },
        shape = Shapes.medium,
        colors = colors,
    )
}