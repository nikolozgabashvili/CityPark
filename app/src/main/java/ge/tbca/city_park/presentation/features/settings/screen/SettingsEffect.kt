package ge.tbca.city_park.presentation.features.settings.screen

sealed interface SettingsEffect {
    data class NavigateToSetting(val id: String) : SettingsEffect
    data object NavigateBack : SettingsEffect
}