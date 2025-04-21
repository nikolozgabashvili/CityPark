package com.example.core.designsystem.components.button.icon_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.designsystem.components.button.base.ButtonDefaults
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.CheckMarkIcon
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.ErrorIcon
import com.example.core.designsystem.util.AppPreview

@Composable
fun TertiaryIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    loading: Boolean = false,
    enabled: Boolean = true,
) {
    val colors = ButtonDefaults.tertiaryColors

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