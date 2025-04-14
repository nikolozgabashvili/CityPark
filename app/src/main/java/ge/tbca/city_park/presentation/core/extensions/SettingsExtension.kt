package ge.tbca.city_park.presentation.core.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material.icons.filled.Language
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.domain.model.Settings

fun Settings.displayIcon(): ImageVector = when (this) {
    Settings.LANGUAGE -> Icons.Default.Language
    Settings.THEME -> Icons.Default.FlashlightOn
}