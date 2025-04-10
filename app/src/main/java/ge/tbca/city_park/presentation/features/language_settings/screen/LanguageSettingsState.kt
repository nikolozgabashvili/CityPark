package ge.tbca.city_park.presentation.features.language_settings.screen

data class LanguageSettingsState(
    val languages: List<String> = emptyList(),
    val flagEmojis: List<String> = emptyList(),
    val selectedLanguage: String = "",
    val isLoading: Boolean = false
)