package ge.tbca.city_park.presentation.core.design_system.components.text_field

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors

object TextInputFieldDefaults {

    val colors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            errorTrailingIconColor = AppColors.onSurfaceVariant
        )
}