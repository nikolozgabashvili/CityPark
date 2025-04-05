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
    val appPadding: Dp = 20.dp,

    val sizeExtraSmall: Dp = 2.dp,
    val sizeSmall: Dp = 4.dp,

    val authTextFieldHeight: Dp = 56.dp,

    val size8: Dp = 8.dp,
    val size16: Dp = 16.dp,
    val size24: Dp = 24.dp,
    val size32: Dp = 32.dp,
    val size230: Dp = 230.dp,
)