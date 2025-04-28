package ge.tbca.city_park.settings.presentation.language_settings.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.settings.domain.model.AppLanguage
import ge.tbca.city_park.settings.domain.usecase.GetSavedLanguageUseCase
import ge.tbca.city_park.settings.domain.usecase.SaveLanguageUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageSettingsViewModel @Inject constructor(
    private val getSavedLanguageUseCase: GetSavedLanguageUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase
) : BaseViewModel<LanguageSettingsState, LanguageSettingsEffect, LanguageSettingsEvent>(
    LanguageSettingsState()
) {

    init {
        viewModelScope.launch {
            getSavedLanguageUseCase().collect { language ->
                updateState { copy(selectedAppLanguage = language) }
            }
        }
    }

    override fun onEvent(event: LanguageSettingsEvent) {
        when (event) {
            is LanguageSettingsEvent.LanguageSelected -> updateSelectedLanguage(event.appLanguage)
            is LanguageSettingsEvent.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(LanguageSettingsEffect.NavigateBack)
        }
    }

    private fun updateSelectedLanguage(appLanguage: AppLanguage) {
        viewModelScope.launch {
            saveLanguageUseCase(appLanguage)
        }
    }
}