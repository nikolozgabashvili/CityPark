package ge.tbca.city_park.settings.presentation.theme_settings.screen

import ge.tbca.city_park.settings.domain.model.AppThemeOption

sealed interface ThemeSettingsEvent {
    data class ThemeSelected(val selectedTheme: AppThemeOption) : ThemeSettingsEvent
    data object BackButtonClicked : ThemeSettingsEvent
}