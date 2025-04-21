package com.example.core.designsystem.components.button.text_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SmartButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.designsystem.components.button.base.ButtonDefaults
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    loading: Boolean = false,
    enabled: Boolean = true
) {
    val colors = ButtonDefaults.secondaryColors

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
private fun PreviewSecondaryButton() {

    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.sizeSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SecondaryButton(
                onClick = {},
                enabled = false,
                text = "Hello Button"
            )

            SecondaryButton(
                onClick = {},
                buttonSize = ButtonSize.LARGE,
                text = "Hello Button"
            )

            SecondaryButton(
                onClick = {},
                startIcon = Icons.Rounded.SmartButton,
                buttonSize = ButtonSize.SMALL,
                text = "Hello Button"
            )
        }
    }

}