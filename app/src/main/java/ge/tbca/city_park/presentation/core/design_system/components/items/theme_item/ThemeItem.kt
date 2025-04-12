package ge.tbca.city_park.presentation.core.design_system.components.items.theme_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.components.button.radio_button.PrimaryRadioButton
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.extensions.displayIcon
import ge.tbca.city_park.presentation.core.extensions.displayName

@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
    onThemeSelected: (AppThemeOption) -> Unit,
    themeDetails: ThemeDetails
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onThemeSelected(themeDetails.themeName) })
    ) {
        Row {
            PrimaryRadioButton(
                text = themeDetails.themeName.displayName(),
                isSelected = themeDetails.themeName == themeDetails.selectedTheme,
                onClick = { onThemeSelected(themeDetails.themeName) }
            )
        }

        Icon(
            modifier = Modifier.align(Alignment.CenterEnd),
            imageVector = themeDetails.themeName.displayIcon(),
            contentDescription = null,
            tint = AppColors.primary
        )

        if (themeDetails.showUnderline) {
            Divider(
                modifier = Modifier
                    .padding(start = Dimen.size32)
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
            themeDetails = ThemeDetails(
                themeName = AppThemeOption.LIGHT,
                selectedTheme = AppThemeOption.LIGHT,
                showUnderline = true
            ),
        )
    }
}