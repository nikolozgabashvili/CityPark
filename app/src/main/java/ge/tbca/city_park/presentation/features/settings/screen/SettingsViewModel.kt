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
            is SettingsEvent.SettingClicked -> navigateToSetting(event.settingId)
            is SettingsEvent.BackButtonClicked -> navigateBack()
        }
    }

    private fun navigateToSetting(settingId: String) {
        viewModelScope.launch {
            sendSideEffect(SettingsEffect.NavigateToSetting(settingId))
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(SettingsEffect.NavigateBack)
        }
    }
}