package ge.tbca.city_park.presentation.features.language_settings.screen

import ge.tbca.city_park.domain.model.AppLanguage

sealed interface LanguageSettingsEvent {
    data class LanguageSelected(val appLanguage: AppLanguage) : LanguageSettingsEvent
}