package ge.tbca.city_park.presentation.core.design_system.components.button.icon_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.core.design_system.components.button.base.ButtonSize
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.CheckMarkIcon
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.theme.ErrorIcon
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun TertiaryIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    loading: Boolean = false,
    enabled: Boolean = true,
) {
    val colors = buttonColors(
        containerColor = AppColors.surface,
        contentColor = AppColors.onSurface,
        disabledContainerColor = AppColors.secondaryContainer,
        disabledContentColor = AppColors.onSecondaryContainer
    )

    BaseIconButton(
        modifier = modifier,
        icon = icon,
        buttonSize = buttonSize,
        loading = loading,
        colors = colors,
        enabled = enabled,
        onClick = onClick
    )

}

@AppPreview
@Composable
private fun PreviewTertiaryIconButton() {

    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.sizeSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TertiaryIconButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                enabled = false,
                icon = CheckMarkIcon
            )

            TertiaryIconButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                enabled = false,
                icon = ErrorIcon
            )
        }
    }

}