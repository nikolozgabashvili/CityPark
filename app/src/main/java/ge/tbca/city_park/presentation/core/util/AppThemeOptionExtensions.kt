package ge.tbca.city_park.presentation.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.AppThemeOption

@Composable
fun AppThemeOption.displayName(): String = when (this) {
    AppThemeOption.LIGHT -> stringResource(R.string.light)
    AppThemeOption.DARK -> stringResource(R.string.dark)
    AppThemeOption.SYSTEM -> stringResource(R.string.system)
}