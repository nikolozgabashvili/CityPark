package ge.tbca.city_park.settings.presentation.language_settings.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.settings.domain.model.AppLanguage
import ge.tbca.city_park.settings.presentation.R

@Composable
fun AppLanguage.displayName(): String = when (this) {
    AppLanguage.GEORGIAN -> stringResource(R.string.georgian)
    AppLanguage.ENGLISH -> stringResource(R.string.english)
}