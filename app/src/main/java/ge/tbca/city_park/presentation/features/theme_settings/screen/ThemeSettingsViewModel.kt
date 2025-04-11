package ge.tbca.city_park.presentation.features.theme_settings.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.WbSunny
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeSettingsViewModel @Inject constructor(
    private val themePreferenceRepository: ThemePreferenceRepository
) : BaseViewModel<ThemeSettingsState, ThemeSettingsEffect, ThemeSettingsEvent>(
    ThemeSettingsState(
        themes = listOf(AppThemeOption.LIGHT, AppThemeOption.DARK, AppThemeOption.SYSTEM),
        endIcons = listOf(
            Icons.Default.WbSunny,
            Icons.Default.ModeNight,
            Icons.Default.Brightness6
        )
    )
) {

    init {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            val savedTheme = themePreferenceRepository.selectedTheme.first()
            updateState { copy(selectedTheme = savedTheme, isLoading = false) }
        }
    }

    override fun onEvent(event: ThemeSettingsEvent) {
        when (event) {
            is ThemeSettingsEvent.SaveThemeClicked -> saveTheme()
            is ThemeSettingsEvent.ThemeSelected -> updateSelectedTheme(event.selectedTheme)
        }
    }

    private fun updateSelectedTheme(theme: AppThemeOption) {
        updateState {
            copy(selectedTheme = theme)
        }
    }

    private fun saveTheme() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            themePreferenceRepository.saveTheme(state.selectedTheme)
            sendSideEffect(ThemeSettingsEffect.ShowSnackbar("Saved")) // TODO from string resources
            updateState { copy(isLoading = false) }
        }
    }
}