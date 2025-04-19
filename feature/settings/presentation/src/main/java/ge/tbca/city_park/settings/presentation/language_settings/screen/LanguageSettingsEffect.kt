package ge.tbca.city_park.settings.presentation.language_settings.screen

sealed interface LanguageSettingsEffect {
    data object NavigateBack : LanguageSettingsEffect
}