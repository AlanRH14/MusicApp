package com.example.musicapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.musicapp.R
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
    isPasswordVisible: Boolean = true,
    onShowPasswordClicked: () -> Unit = {},
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
                IconButton(
                    onClick = onShowPasswordClicked,
                ) {
                    Icon(
                        painter = if (isPasswordVisible) icon else painterResource(R.drawable.ic_eye),
                        contentDescription = trailingDescription
                    )
                }
            }
        },
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        shape = Shapes.medium,
        colors = colors,
    )
}