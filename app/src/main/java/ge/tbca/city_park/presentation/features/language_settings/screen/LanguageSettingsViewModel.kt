package ge.tbca.city_park.presentation.features.language_settings.screen

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.model.Language
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
        }
    }

    private fun updateSelectedLanguage(language: Language) {
        updateState {
            copy(selectedLanguage = language)
        }
        saveLanguage()
    }

    private fun saveLanguage() {
        // TODO save language
    }
}