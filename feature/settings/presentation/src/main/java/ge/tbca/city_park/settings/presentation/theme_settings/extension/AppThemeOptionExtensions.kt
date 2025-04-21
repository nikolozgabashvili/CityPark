package ge.tbca.city_park.settings.presentation.theme_settings.extension

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrightnessMedium
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.settings.domain.model.AppThemeOption
import ge.tbca.city_park.settings.presentation.R

@Composable
fun AppThemeOption.displayName(): String = when (this) {
    AppThemeOption.LIGHT -> stringResource(R.string.light)
    AppThemeOption.DARK -> stringResource(R.string.dark)
    AppThemeOption.SYSTEM -> stringResource(R.string.system)
}

fun AppThemeOption.displayIcon(): ImageVector = when (this) {
    AppThemeOption.LIGHT -> Icons.Rounded.WbSunny
    AppThemeOption.DARK -> Icons.Rounded.DarkMode
    AppThemeOption.SYSTEM -> Icons.Rounded.BrightnessMedium
}