package ge.tbca.city_park.presentation.core.design_system.components.button.base

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.transparent

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
            containerColor = AppColors.transparent,
            contentColor = AppColors.onSurface,
            disabledContainerColor = AppColors.transparent,
            disabledContentColor = AppColors.onSecondaryContainer
        )

    val secondaryColors:ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = AppColors.secondaryContainer,
            contentColor = AppColors.onSecondaryContainer,
            disabledContainerColor = AppColors.surface,
            disabledContentColor = AppColors.onSurface
        )
}