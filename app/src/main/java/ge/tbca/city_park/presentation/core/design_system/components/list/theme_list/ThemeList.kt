package ge.tbca.city_park.presentation.core.design_system.components.list.theme_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun ThemeList(
    modifier: Modifier = Modifier,
    onThemeSelected: (String) -> Unit,
    themes: List<String>,
    endIcons: List<ImageVector>,
    selectedTheme: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        themes.forEachIndexed { index, theme ->
            ThemeItem(
                onThemeSelected = onThemeSelected,
                themeName = theme,
                endIcon = endIcons[index],
                selectedTheme = selectedTheme,
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
            themes = listOf("თეთრი", "მუქი", "სისტემური"),
            endIcons = listOf(
                Icons.Default.WbSunny,
                Icons.Default.ModeNight,
                Icons.Default.Brightness6
            ),
            selectedTheme = "თეთრი"
        )
    }
}