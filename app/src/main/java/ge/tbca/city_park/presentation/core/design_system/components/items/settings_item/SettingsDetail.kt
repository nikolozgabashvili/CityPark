package ge.tbca.city_park.presentation.core.design_system.components.items.settings_item

import ge.tbca.city_park.domain.model.Settings

data class SettingsDetail(
    val id: Settings,
    val name: String,
    val endText: String? = null
)