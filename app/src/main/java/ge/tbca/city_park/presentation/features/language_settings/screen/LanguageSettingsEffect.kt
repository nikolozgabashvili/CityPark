package ge.tbca.city_park.presentation.features.language_settings.screen

sealed interface LanguageSettingsEffect {
    data object NavigateBack : LanguageSettingsEffect
}