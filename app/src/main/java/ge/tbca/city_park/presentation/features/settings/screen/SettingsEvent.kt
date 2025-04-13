package ge.tbca.city_park.presentation.features.settings.screen



sealed interface SettingsEvent {
    data object BackButtonClicked : SettingsEvent
    data object NavigateToLanguageSettings : SettingsEvent
    data object NavigateToThemeSettings : SettingsEvent
}