package ge.tbca.city_park.settings.presentation.settings.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.settings.domain.usecase.GetSavedLanguageUseCase
import ge.tbca.city_park.settings.domain.usecase.GetSavedThemeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSavedThemeUseCase: GetSavedThemeUseCase,
    private val getSavedLanguageUseCase: GetSavedLanguageUseCase,
) : BaseViewModel<SettingsState, SettingsEffect, SettingsEvent>(SettingsState()) {

    init {
        viewModelScope.launch {
            getSavedThemeUseCase().collect { theme ->
                updateState { copy(currentThemeMode = theme) }
            }
        }

        viewModelScope.launch {
            getSavedLanguageUseCase().collect { language ->
                updateState { copy(currentLanguage = language) }
            }
        }
    }

    override fun onEvent(event: SettingsEvent) {
        when (event) {
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
}