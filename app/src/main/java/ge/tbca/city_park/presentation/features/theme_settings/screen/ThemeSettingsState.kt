package ge.tbca.city_park.presentation.features.theme_settings.screen

import androidx.compose.ui.graphics.vector.ImageVector

data class ThemeSettingsState(
    val themes: List<String> = emptyList(),
    val endIcons: List<ImageVector> = emptyList(),
    val selectedTheme: String = "",
    val isLoading: Boolean = false
)