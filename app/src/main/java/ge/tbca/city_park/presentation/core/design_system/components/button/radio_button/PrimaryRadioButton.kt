package ge.tbca.city_park.presentation.core.design_system.components.button.radio_button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

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
                style = AppTypography.bodyMedium,
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