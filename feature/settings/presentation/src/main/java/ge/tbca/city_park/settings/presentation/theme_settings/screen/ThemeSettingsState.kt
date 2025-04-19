package ge.tbca.city_park.settings.presentation.theme_settings.screen

import ge.tbca.city_park.settings.domain.model.AppThemeOption

data class ThemeSettingsState(
    val themes: List<AppThemeOption> = AppThemeOption.entries,
    val selectedTheme: AppThemeOption = AppThemeOption.SYSTEM,
    val isLoading: Boolean = false
)