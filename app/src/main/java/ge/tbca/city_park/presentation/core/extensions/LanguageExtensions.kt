package ge.tbca.city_park.presentation.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.AppLanguage

@Composable
fun AppLanguage.displayName(): String = when (this) {
    AppLanguage.GEORGIAN -> stringResource(R.string.georgian)
    AppLanguage.ENGLISH -> stringResource(R.string.english)
}