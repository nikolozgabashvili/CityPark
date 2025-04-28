package ge.tbca.city_park.settings.presentation.settings.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
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
        observeSavedTheme()
        observeSavedLanguage()
    }

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.NavigateToLanguageSettings -> navigateToLanguageSettings()
            is SettingsEvent.NavigateToThemeSettings -> navigateToThemeSettings()
            is SettingsEvent.BackButtonClicked -> navigateBack()
        }
    }

    private fun observeSavedLanguage() {
        viewModelScope.launch {
            getSavedLanguageUseCase().collect { language ->
                updateState { copy(currentLanguage = language) }
            }
        }
    }

    private fun observeSavedTheme() {
        viewModelScope.launch {
            getSavedThemeUseCase().collect { theme ->
                updateState { copy(currentThemeMode = theme) }
            }
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