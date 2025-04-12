package ge.tbca.city_park.presentation.features.settings.screen

import ge.tbca.city_park.domain.model.Settings

sealed interface SettingsEvent {
    data class SettingClicked(val setting: Settings) : SettingsEvent
    data object BackButtonClicked : SettingsEvent
}