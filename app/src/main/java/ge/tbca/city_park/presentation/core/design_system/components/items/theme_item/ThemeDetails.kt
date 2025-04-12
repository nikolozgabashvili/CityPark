package ge.tbca.city_park.presentation.core.design_system.components.items.theme_item

import ge.tbca.city_park.domain.model.AppThemeOption

data class ThemeDetails(
    val themeName: AppThemeOption,
    val selectedTheme: AppThemeOption,
    val showUnderline: Boolean
)