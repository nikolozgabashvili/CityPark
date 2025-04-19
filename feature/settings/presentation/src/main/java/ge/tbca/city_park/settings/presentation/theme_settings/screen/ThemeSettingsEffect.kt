package ge.tbca.city_park.settings.presentation.theme_settings.screen

sealed interface ThemeSettingsEffect {
    data object NavigateBack : ThemeSettingsEffect
}