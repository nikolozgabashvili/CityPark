package com.example.core.designsystem.components.dialog

import androidx.compose.runtime.Composable

@Composable
fun PermissionDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onDismissRequest: () -> Unit,
    canDismiss: Boolean = true,
    onConfirm: () -> Unit
) {
    BaseAlertDialog(
        setDismissible = canDismiss,
        onDismiss = onDismissRequest,
        positiveButtonText = positiveButtonText,
        title = title,
        message = message,
        onPositiveButtonClick = onConfirm
    )

}

