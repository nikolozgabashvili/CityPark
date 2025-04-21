package ge.tbca.city_park.settings.presentation.settings.screen

import ge.tbca.city_park.settings.domain.model.AppLanguage
import ge.tbca.city_park.settings.domain.model.AppThemeOption


data class SettingsState(
    val isLoading: Boolean = false,
    val currentThemeMode: AppThemeOption = AppThemeOption.SYSTEM,
    val currentLanguage: AppLanguage = AppLanguage.GEORGIAN
)
