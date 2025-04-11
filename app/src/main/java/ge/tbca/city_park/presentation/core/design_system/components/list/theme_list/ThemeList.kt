package ge.tbca.city_park.presentation.core.design_system.components.list.theme_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.displayName

@Composable
fun ThemeList(
    modifier: Modifier = Modifier,
    onThemeSelected: (AppThemeOption) -> Unit,
    themes: List<AppThemeOption>,
    endIcons: List<ImageVector>,
    selectedTheme: AppThemeOption
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        themes.forEachIndexed { index, theme ->
            ThemeItem(
                onThemeSelected = { onThemeSelected(theme) },
                themeName = theme.displayName(),
                endIcon = endIcons[index],
                selectedTheme = selectedTheme.displayName(),
                showUnderline = index != themes.lastIndex
            )
        }
    }
}

@Composable
@AppPreview
private fun ThemeListPreview() {
    AppTheme {
        ThemeList(
            onThemeSelected = {},
            themes = listOf(AppThemeOption.LIGHT, AppThemeOption.DARK, AppThemeOption.SYSTEM),
            endIcons = listOf(
                Icons.Default.WbSunny,
                Icons.Default.ModeNight,
                Icons.Default.Brightness6
            ),
            selectedTheme = AppThemeOption.LIGHT
        )
    }
}