package com.example.core.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.TextStyles

@Composable
fun PermissionDialog(
    title: String,
    message: String,
    positiveButtonText:String,
    onDismissRequest: () -> Unit,
    canDismiss: Boolean = true,
    onConfirm: () -> Unit
) {
    AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = canDismiss,
            dismissOnClickOutside = canDismiss,
        ),
        onDismissRequest = onDismissRequest,
        confirmButton = {
            PrimaryButton(
                onClick = onConfirm,
                buttonSize = ButtonSize.MEDIUM,
                text = positiveButtonText,
            )
        },
        title = {
            Text(
                text = title,
                style = TextStyles.titleLarge,
                color = AppColors.onSurface
            )
        },
        text = {
            Text(
                text = message,
                style = TextStyles.bodyLarge,
                color = AppColors.onSurface
            )
        }
    )

}