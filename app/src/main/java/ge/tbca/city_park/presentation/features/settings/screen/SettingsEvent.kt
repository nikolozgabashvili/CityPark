package ge.tbca.city_park.presentation.features.settings.screen

sealed interface SettingsEvent {
    data class SettingClicked(val settingId: String) : SettingsEvent
    data object BackButtonClicked : SettingsEvent
}