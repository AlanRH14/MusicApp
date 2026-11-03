package com.example.musicapp.presentation.login.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.musicapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopAppBar(
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent
                ),
                onClick = onBackPress,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.image_back),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
