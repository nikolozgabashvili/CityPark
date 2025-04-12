package ge.tbca.city_park.presentation.features.theme_settings.screen

import ge.tbca.city_park.domain.model.AppThemeOption

data class ThemeSettingsState(
    val themes: List<AppThemeOption> = listOf(
        AppThemeOption.SYSTEM,
        AppThemeOption.LIGHT,
        AppThemeOption.DARK
    ),
    val selectedTheme: AppThemeOption = AppThemeOption.SYSTEM,
    val isLoading: Boolean = false
)