package ge.tbca.city_park.presentation.features.settings.screen

import ge.tbca.city_park.presentation.core.design_system.components.list.settings_list.SettingsDetail

data class SettingsState(
    val settingsList: List<SettingsDetail> = emptyList(),
    val isLoading: Boolean = false
)
