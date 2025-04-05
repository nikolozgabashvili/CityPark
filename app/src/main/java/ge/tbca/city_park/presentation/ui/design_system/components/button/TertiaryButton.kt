package ge.tbca.city_park.presentation.ui.design_system.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SmartButton
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.design_system.util.AppPreview

@Composable
fun TertiaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    loading: Boolean = false,
    enabled: Boolean = true
) {
    val colors = buttonColors(
        containerColor = AppColors.surface,
        contentColor = AppColors.onSurface,
        disabledContainerColor = AppColors.secondaryContainer,
        disabledContentColor = AppColors.onSecondaryContainer
    )

    BaseButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        buttonSize = buttonSize,
        startIcon = startIcon,
        endIcon = endIcon,
        loading = loading,
        colors = colors,
        enabled = enabled
    )
}

@AppPreview
@Composable
private fun PreviewTertiaryButton() {

    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.sizeSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TertiaryButton(
                onClick = {},
                enabled = false,
                text = "Hello Button"
            )

            TertiaryButton(
                onClick = {},
                loading = false,
                buttonSize = ButtonSize.LARGE,
                text = "Hello Button"
            )

            TertiaryButton(
                onClick = {},
                startIcon = Icons.Rounded.SmartButton,
                buttonSize = ButtonSize.SMALL,
                text = "Hello Button"
            )
        }
    }

}