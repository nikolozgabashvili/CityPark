package ge.tbca.city_park.presentation.features.theme_settings.screen

import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.domain.model.AppThemeOption

data class ThemeSettingsState(
    val themes: List<AppThemeOption> = emptyList(),
    val endIcons: List<ImageVector> = emptyList(),
    val selectedTheme: AppThemeOption = AppThemeOption.SYSTEM,
    val isLoading: Boolean = false
)