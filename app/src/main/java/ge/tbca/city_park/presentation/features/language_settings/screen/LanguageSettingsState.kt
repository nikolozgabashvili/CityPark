package ge.tbca.city_park.presentation.features.language_settings.screen

import ge.tbca.city_park.domain.model.Language

data class LanguageSettingsState(
    val languages: List<Language> = emptyList(),
    val selectedLanguage: Language = Language.GEORGIAN,
    val isLoading: Boolean = false
)