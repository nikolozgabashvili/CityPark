package ge.tbca.city_park.settings.presentation.settings.screen

sealed interface SettingsEffect {
    data object NavigateBack : SettingsEffect
    data object NavigateToLanguageSettings: SettingsEffect
    data object NavigateToThemeSettings: SettingsEffect
}