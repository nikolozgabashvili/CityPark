package com.example.core.designsystem.components.button.radio_button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview

@Composable
fun PrimaryRadioButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    isSelected: Boolean,
) {
    Row(
        modifier = modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = null
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )

        text?.let {
            Spacer(modifier = Modifier.width(Dimen.size6))
            Text(
                style = TextStyles.bodyMedium,
                color = AppColors.primary,
                text = it
            )
        }
    }
}

@Composable
@AppPreview
private fun PrimaryRadioButtonPreview() {
    AppTheme {
        PrimaryRadioButton(
            isSelected = true,
            text = "text",
            onClick = {}
        )
    }
}

@Composable
@AppPreview
private fun PrimaryRadioButtonPreviewUnselected() {
    AppTheme {
        PrimaryRadioButton(
            isSelected = false,
            text = "text",
            onClick = {}
        )
    }
}