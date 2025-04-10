package ge.tbca.city_park.presentation.core.design_system.components.list.settings_list

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsDetail(
    val id: String,
    val name: String,
    val startIcon: ImageVector,
    val endText: String? = null
)