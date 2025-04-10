package ge.tbca.city_park.presentation.features.theme_settings.screen

sealed interface ThemeSettingsEvent {
    data class ThemeSelected(val theme: String) : ThemeSettingsEvent
    data object SaveThemeClicked: ThemeSettingsEvent
}