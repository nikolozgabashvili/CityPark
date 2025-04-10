package ge.tbca.city_park.presentation.core.design_system.components.list.theme_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.components.button.radio_button.PrimaryRadioButton
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
    onThemeSelected: (String) -> Unit,
    themeName: String,
    endIcon: ImageVector,
    selectedTheme: String,
    showUnderline: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onThemeSelected(themeName) }
    ) {
        Row {
            PrimaryRadioButton(
                text = themeName,
                isSelected = themeName == selectedTheme,
                onClick = { onThemeSelected(themeName) }
            )
        }

        Icon(
            modifier = Modifier.align(Alignment.CenterEnd),
            imageVector = endIcon,
            contentDescription = null,
            tint = AppColors.primary
        )

        if (showUnderline) {
            Divider(
                modifier = Modifier
                    .padding(start = Dimen.size50)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
@AppPreview
private fun ThemeItemPreview() {
    AppTheme {
        ThemeItem(
            onThemeSelected = {},
            themeName = "თეთრი",
            endIcon = Icons.Default.WbSunny,
            selectedTheme = "თეთრი",
            showUnderline = true
        )
    }
}