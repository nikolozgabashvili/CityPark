package ge.tbca.city_park.presentation.features.theme_settings.screen

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeSettingsViewModel @Inject constructor() :
    BaseViewModel<ThemeSettingsState, ThemeSettingsEffect, ThemeSettingsEvent>(ThemeSettingsState()) {

    override fun onEvent(event: ThemeSettingsEvent) {
        when (event) {
            is ThemeSettingsEvent.SaveThemeClicked -> saveTheme()
            is ThemeSettingsEvent.ThemeSelected -> updateSelectedTheme(event.theme)
        }
    }

    private fun updateSelectedTheme(theme: String) {
        updateState {
            copy(selectedTheme = theme)
        }
    }

    private fun saveTheme() {
        // TODO save theme
    }
}