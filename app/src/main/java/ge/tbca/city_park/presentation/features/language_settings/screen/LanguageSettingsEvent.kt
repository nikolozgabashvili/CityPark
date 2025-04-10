package ge.tbca.city_park.presentation.features.language_settings.screen

sealed interface LanguageSettingsEvent {
    data class LanguageSelected(val language: String) : LanguageSettingsEvent
    data object SaveLanguageClicked: LanguageSettingsEvent
}