package ge.tbca.city_park.presentation.features.settings.screen

import ge.tbca.city_park.domain.model.Settings

sealed interface SettingsEffect {
    data class NavigateToSetting(val id: Settings) : SettingsEffect
    data object NavigateBack : SettingsEffect
}