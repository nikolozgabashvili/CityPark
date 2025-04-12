package ge.tbca.city_park.presentation.core.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.AppThemeOption

@Composable
fun AppThemeOption.displayName(): String = when (this) {
    AppThemeOption.LIGHT -> stringResource(R.string.light)
    AppThemeOption.DARK -> stringResource(R.string.dark)
    AppThemeOption.SYSTEM -> stringResource(R.string.system)
}

fun AppThemeOption.displayIcon(): ImageVector = when (this) {
    AppThemeOption.LIGHT -> Icons.Default.WbSunny
    AppThemeOption.DARK -> Icons.Default.ModeNight
    AppThemeOption.SYSTEM -> Icons.Default.Brightness6
}