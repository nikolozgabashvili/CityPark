package ge.tbca.city_park.presentation.features.language_settings.screen

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageSettingsViewModel @Inject constructor() :
    BaseViewModel<LanguageSettingsState, LanguageSettingsEffect, LanguageSettingsEvent>(
        LanguageSettingsState()
    ) {

    override fun onEvent(event: LanguageSettingsEvent) {
        when (event) {
            is LanguageSettingsEvent.LanguageSelected -> updateSelectedLanguage(event.language)
            is LanguageSettingsEvent.SaveLanguageClicked -> saveLanguage()
        }
    }

    private fun updateSelectedLanguage(language: String) {
        updateState {
            copy(selectedLanguage = language)
        }
    }

    private fun saveLanguage() {
        // TODO save language
    }
}