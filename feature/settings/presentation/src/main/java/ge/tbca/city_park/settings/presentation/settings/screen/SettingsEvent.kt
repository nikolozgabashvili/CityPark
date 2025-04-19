package ge.tbca.city_park.settings.presentation.settings.screen

sealed interface SettingsEvent {
    data object BackButtonClicked : SettingsEvent
    data object NavigateToLanguageSettings : SettingsEvent
    data object NavigateToThemeSettings : SettingsEvent
}