package com.example.core.designsystem.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.button.text_button.SecondaryButton
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview

@Composable
fun BaseAlertDialog(
    onDismiss: () -> Unit,
    setDismissible: Boolean = true,
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: (() -> Unit)? = null,
    positiveButtonText: String,
    negativeButtonText: String? = null,
    title: String,
    message: String? = null,
) {

    AlertDialog(
        containerColor = AppColors.background,
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = setDismissible,
            dismissOnClickOutside = setDismissible,
        ),
        confirmButton = {
            PrimaryButton(
                onClick = onPositiveButtonClick,
                text = positiveButtonText,
                buttonSize = ButtonSize.MEDIUM,
            )
        },
        dismissButton = negativeButtonText?.let {
            {
                SecondaryButton(
                    onClick = { onNegativeButtonClick?.invoke() },
                    text = negativeButtonText,
                    buttonSize = ButtonSize.MEDIUM,
                )
            }
        },
        title = {
            Text(text = title, style = TextStyles.titleLarge, color = AppColors.onSurface)
        },
        text = {
            message?.let {
                Text(text = it, style = TextStyles.bodyLarge, color = AppColors.onSurface)
            }
        }
    )
}


@AppPreview
@Composable
private fun BaseAlertDialogPrev() {

    AppTheme {
        BaseAlertDialog(
            onDismiss = {},
            negativeButtonText = "negative",
            onPositiveButtonClick = {},
            positiveButtonText = "heheheheh",
            title = "Title",
            message = "This is a message",
        )

    }


}