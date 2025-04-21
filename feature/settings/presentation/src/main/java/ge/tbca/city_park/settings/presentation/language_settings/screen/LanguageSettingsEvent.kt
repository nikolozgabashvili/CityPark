package ge.tbca.city_park.settings.presentation.language_settings.screen

import ge.tbca.city_park.settings.domain.model.AppLanguage


sealed interface LanguageSettingsEvent {
    data class LanguageSelected(val appLanguage: AppLanguage) : LanguageSettingsEvent
    data object NavigateBack: LanguageSettingsEvent
}