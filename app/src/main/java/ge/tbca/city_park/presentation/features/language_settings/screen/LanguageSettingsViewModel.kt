package ge.tbca.city_park.presentation.features.language_settings.screen

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.model.AppLanguage
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageSettingsViewModel @Inject constructor() :
    BaseViewModel<LanguageSettingsState, LanguageSettingsEffect, LanguageSettingsEvent>(
        LanguageSettingsState()
    ) {

    override fun onEvent(event: LanguageSettingsEvent) {
        when (event) {
            is LanguageSettingsEvent.LanguageSelected -> updateSelectedLanguage(event.appLanguage)
        }
    }

    private fun updateSelectedLanguage(appLanguage: AppLanguage) {
        updateState {
            copy(selectedAppLanguage = appLanguage)
        }
        saveLanguage()
    }

    private fun saveLanguage() {

    }
}