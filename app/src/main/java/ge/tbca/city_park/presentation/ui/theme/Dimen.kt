package ge.tbca.city_park.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val LocalDimen = compositionLocalOf { AppDimens() }

val Dimen: AppDimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimen.current


data class AppDimens(
    val sizeExtraSmall: Dp = 2.dp,
    val sizeSmall: Dp = 4.dp
)