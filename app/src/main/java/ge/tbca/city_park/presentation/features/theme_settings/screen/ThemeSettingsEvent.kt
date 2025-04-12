package ge.tbca.city_park.presentation.features.theme_settings.screen

import ge.tbca.city_park.domain.model.AppThemeOption

sealed interface ThemeSettingsEvent {
    data class ThemeSelected(val selectedTheme: AppThemeOption) : ThemeSettingsEvent
    data object BackButtonClicked : ThemeSettingsEvent
}