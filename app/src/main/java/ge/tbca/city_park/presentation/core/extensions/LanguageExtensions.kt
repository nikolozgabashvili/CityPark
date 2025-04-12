package ge.tbca.city_park.presentation.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.Language

@Composable
fun Language.displayName(): String = when (this) {
    Language.GEORGIAN -> stringResource(R.string.georgian)
    Language.ENGLISH -> stringResource(R.string.english)
}