package com.example.musicapp.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import com.example.musicapp.R
import com.example.musicapp.ui.theme.Shapes

@Composable
fun MusicAppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    leadingDescription: String? = null,
    trailingIcon: Painter? = null,
    trailingDescription: String? = null,
    isPasswordVisible: Boolean = true,
    isPasswordTextField: Boolean = false,
    onTrailingIconClicked: () -> Unit = {},
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { label?.let { Text(label) } },
        placeholder = { placeholder?.let { Text(placeholder) } },
        leadingIcon =
            leadingIcon?.let { icon ->
                {
                    Icon(
                        painter = icon,
                        contentDescription = leadingDescription,
                    )
                }
            },
        trailingIcon = trailingIcon?.let { icon ->
            {
                IconButton(
                    onClick = onTrailingIconClicked,
                ) {
                    Icon(
                        painter = if (isPasswordTextField) {
                            if (isPasswordVisible) icon else painterResource(id = R.drawable.ic_eye)
                        } else {
                            icon
                        },
                        contentDescription = trailingDescription
                    )
                }
            }
        },
        isError = isError,
        supportingText = {
            if (isError && errorMessage != null) {
                Text(errorMessage)
            }
        },
        singleLine = singleLine,
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            visualTransformation
        },
        shape = Shapes.medium,
        colors = colors,
    )
}