package ge.tbca.city_park.app.activity

import ge.tbca.city_park.settings.domain.model.AppLanguage

sealed interface MainActivityEffect {
    data class LanguageChanged(val language: AppLanguage) : MainActivityEffect

}
