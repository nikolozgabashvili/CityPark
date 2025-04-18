package ge.tbca.city_park.presentation.features.language_settings.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.GetCurrentLanguageUseCase
import ge.tbca.city_park.domain.core.usecase.SaveLanguageUseCase
import ge.tbca.city_park.domain.model.AppLanguage
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageSettingsViewModel @Inject constructor(
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase
) : BaseViewModel<LanguageSettingsState, LanguageSettingsEffect, LanguageSettingsEvent>(
    LanguageSettingsState()
) {

    init {
        viewModelScope.launch {
            getCurrentLanguageUseCase().collect { language ->
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