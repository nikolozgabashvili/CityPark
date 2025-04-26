package com.example.core.designsystem.components.button.base

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import com.example.core.designsystem.theme.AppColors

object ButtonDefaults {

    val primaryColors: ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = AppColors.inverseSurface,
            contentColor = AppColors.inverseOnSurface,
            disabledContainerColor = AppColors.secondaryContainer,
            disabledContentColor = AppColors.onSecondaryContainer
        )

    val tertiaryColors:ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = AppColors.background,
            contentColor = AppColors.onSurface,
            disabledContainerColor = AppColors.background,
            disabledContentColor = AppColors.onSecondaryContainer
        )

    val secondaryColors:ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = AppColors.surface,
            contentColor = AppColors.onSurface,
            disabledContainerColor = AppColors.secondaryContainer,
            disabledContentColor = AppColors.onSecondaryContainer
        )
}