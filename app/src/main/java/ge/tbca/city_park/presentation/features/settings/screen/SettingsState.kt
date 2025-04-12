package ge.tbca.city_park.presentation.features.settings.screen

import ge.tbca.city_park.presentation.core.design_system.components.items.settings_item.SettingsDetail

data class SettingsState(
    val settingsList: List<SettingsDetail> = emptyList(),
    val isLoading: Boolean = false
)
