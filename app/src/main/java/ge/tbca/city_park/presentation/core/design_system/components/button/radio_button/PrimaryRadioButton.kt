package ge.tbca.city_park.presentation.core.design_system.components.button.radio_button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun PrimaryRadioButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.clickable(onClick = onClick, enabled = !isSelected)
    ) {
        RadioButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            selected = isSelected,
            onClick = { if (!isSelected) onClick() }
        )

        text?.let {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
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
            text = "ქართული",
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
            text = "English",
            onClick = {}
        )
    }
}