package com.example.core.designsystem.components.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview

@Composable
fun PrimaryCheckbox(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    isChecked: Boolean,
    isLoading: Boolean
) {
    Row(
        modifier = modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = null
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            enabled = !isLoading,
            checked = isChecked,
            onCheckedChange = { onClick() }
        )
        text?.let {
            Text(
                style = TextStyles.bodyMedium,
                color = AppColors.primary,
                text = it
            )
        }
    }
}

@AppPreview
@Composable
private fun PrimaryCheckboxPreview() {
    AppTheme {
        PrimaryCheckbox(
            isChecked = true,
            onClick = {},
            text = "text",
            isLoading = false
        )
    }
}

@AppPreview
@Composable
private fun PrimaryCheckboxPreviewUnselected() {
    AppTheme {
        PrimaryCheckbox(
            isChecked = false,
            onClick = {},
            text = "text",
            isLoading = false
        )
    }
}