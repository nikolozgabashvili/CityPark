package ge.tbca.city_park.presentation.features.settings.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() :
    BaseViewModel<SettingsState, SettingsEffect, SettingsEvent>(SettingsState()) {

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.BackButtonClicked -> navigateBack()
            SettingsEvent.NavigateToLanguageSettings -> navigateToLanguageSettings()
            SettingsEvent.NavigateToThemeSettings -> navigateToThemeSettings()
        }
    }

    private fun navigateToLanguageSettings() {
        viewModelScope.launch {
            sendSideEffect(SettingsEffect.NavigateToLanguageSettings)
        }
    }

    private fun navigateToThemeSettings() {
        viewModelScope.launch {
            sendSideEffect(SettingsEffect.NavigateToThemeSettings)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(SettingsEffect.NavigateBack)
        }
    }
}