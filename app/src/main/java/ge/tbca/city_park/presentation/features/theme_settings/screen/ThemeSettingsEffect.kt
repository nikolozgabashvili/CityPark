package ge.tbca.city_park.presentation.features.theme_settings.screen

sealed interface ThemeSettingsEffect {
    data object NavigateBack : ThemeSettingsEffect
    data class ShowSnackbar(val message: String) : ThemeSettingsEffect
}