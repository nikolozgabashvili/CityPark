package ge.tbca.city_park.presentation.ui.design_system.components.button.text_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SmartButton
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.ui.design_system.components.button.base.ButtonSize
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.design_system.util.AppPreview

@Composable
fun PrimaryButton(
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
        containerColor = AppColors.inverseSurface,
        contentColor = AppColors.inverseOnSurface,
        disabledContainerColor = AppColors.secondaryContainer,
        disabledContentColor = AppColors.onSecondaryContainer
    )

    BaseTextButton(
        modifier = modifier,
        onClick = onClick,
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
private fun PreviewPrimaryButton() {

    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.sizeSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
                onClick = {},
                enabled = false,
                text = "Hello Button"
            )

            PrimaryButton(
                onClick = {},
                buttonSize = ButtonSize.LARGE,
                text = "Hello Button"
            )

            PrimaryButton(
                onClick = {},
                startIcon = Icons.Rounded.SmartButton,
                buttonSize = ButtonSize.SMALL,
                text = "Hello Button"
            )
        }
    }

}