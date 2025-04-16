package ge.tbca.city_park.presentation.features.settings.screen

import ge.tbca.city_park.domain.model.AppLanguage
import ge.tbca.city_park.domain.model.AppThemeOption


data class SettingsState(
    val isLoading: Boolean = false,
    val currentThemeMode: AppThemeOption = AppThemeOption.SYSTEM,
    val currentAppLanguage: AppLanguage = AppLanguage.GEORGIAN
)
