package ge.tbca.city_park.presentation.features.settings.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSavedThemeUseCase: GetSavedThemeUseCase,
) : BaseViewModel<SettingsState, SettingsEffect, SettingsEvent>(SettingsState()) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedThemeUseCase().collect { theme ->
                updateState { copy(currentThemeMode = theme) }

            }
        }
    }

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.BackButtonClicked -> navigateBack()
            is SettingsEvent.NavigateToLanguageSettings -> navigateToLanguageSettings()
            is SettingsEvent.NavigateToThemeSettings -> navigateToThemeSettings()
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