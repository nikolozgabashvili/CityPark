package ge.tbca.city_park.presentation.core.design_system.components.items.language_item

import ge.tbca.city_park.domain.model.Language

data class LanguageDetails(
    val language: Language,
    val selectedLanguage: Language,
    val showUnderline: Boolean
)