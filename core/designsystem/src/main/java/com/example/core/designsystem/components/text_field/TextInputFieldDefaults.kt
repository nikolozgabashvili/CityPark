package com.example.core.designsystem.components.text_field

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.transparent

object TextInputFieldDefaults {

    val colors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.colors(
            unfocusedContainerColor = AppColors.transparent,
            errorContainerColor = AppColors.transparent,
            disabledContainerColor = AppColors.transparent,
            focusedContainerColor = AppColors.transparent,
            errorTrailingIconColor = AppColors.onSurfaceVariant
        )
}