package ge.tbca.city_park.settings.presentation.language_settings.screen

import ge.tbca.city_park.settings.domain.model.AppLanguage


data class LanguageSettingsState(
    val appLanguages: List<AppLanguage> = listOf(AppLanguage.GEORGIAN, AppLanguage.ENGLISH),
    val selectedAppLanguage: AppLanguage = AppLanguage.GEORGIAN,
    val isLoading: Boolean = false
)