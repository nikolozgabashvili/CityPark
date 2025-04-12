package ge.tbca.city_park.presentation.features.theme_settings.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.domain.core.usecase.SaveThemeUseCase
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeSettingsViewModel @Inject constructor(
    private val getSavedThemeUseCase: GetSavedThemeUseCase,
    private val saveThemeUseCase: SaveThemeUseCase
) : BaseViewModel<ThemeSettingsState, ThemeSettingsEffect, ThemeSettingsEvent>(ThemeSettingsState()) {

    init {
        viewModelScope.launch {
            getSavedThemeUseCase().collect {
                updateState {
                    copy(selectedTheme = it)
                }
            }
        }
    }

    override fun onEvent(event: ThemeSettingsEvent) {
        when (event) {
            is ThemeSettingsEvent.ThemeSelected -> updateSelectedTheme(event.selectedTheme)
            is ThemeSettingsEvent.BackButtonClicked -> navigateBack()
        }
    }

    private fun updateSelectedTheme(theme: AppThemeOption) {
        viewModelScope.launch {
            saveThemeUseCase(theme)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(ThemeSettingsEffect.NavigateBack)
        }
    }
}