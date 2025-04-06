package ge.tbca.city_park.presentation.ui.design_system.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ge.tbca.city_park.presentation.ui.design_system.util.AppPreview
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.Dimen

@Composable
fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    loading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    enabled: Boolean = true
) {

    val buttonParams = buttonSize.getButtonParams()
    val loaderRes = loaderResourceId(enabled, colors)

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(loaderRes)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = loading,
        iterations = Int.MAX_VALUE
    )

    val isEnabled = enabled && !loading

    Button(
        contentPadding = PaddingValues(horizontal = buttonParams.outPadding),
        modifier = modifier.height(buttonParams.buttonHeight),
        onClick = onClick,
        enabled = isEnabled,
        colors = colors
    ) {
        Box(contentAlignment = Alignment.Center) {

            val contentAlpha = if (loading) 0f else 1f
            if (loading) {

                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .size(buttonParams.iconSize)
                )

            }

            Row(
                Modifier
                    .alpha(contentAlpha)
            ) {
                startIcon?.let { vector ->
                    Icon(
                        imageVector = vector,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = buttonParams.inPadding)
                            .size(buttonParams.iconSize)
                    )
                }

                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = text,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = buttonParams.textStyle
                )

                endIcon?.let { vector ->
                    Icon(
                        imageVector = vector,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = buttonParams.inPadding)
                            .size(buttonParams.iconSize)
                    )

                }
            }
        }

    }
}


@Composable
@AppPreview
private fun EnabledPrimaryButtonPreview() {
    AppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BaseButton(

                onClick = {},
                buttonSize = ButtonSize.LARGE,
                text = "Login",
                startIcon = Icons.Rounded.Lock,
                enabled = true
            )

            Spacer(modifier = Modifier.height(Dimen.sizeSmall))

            BaseButton(
                onClick = {},
                startIcon = Icons.Rounded.Lock,
                buttonSize = ButtonSize.MEDIUM,
                text = "Login",
                enabled = true
            )
            Spacer(modifier = Modifier.height(Dimen.sizeSmall))

            BaseButton(
                onClick = {},
                startIcon = Icons.Rounded.Lock,
                endIcon = Icons.Rounded.Lock,
                buttonSize = ButtonSize.SMALL,
                text = "Login",
                enabled = true
            )
        }
    }
}

@Composable
@AppPreview
private fun DisabledPrimaryButtonPreview() {
    AppTheme {
        Column {
            BaseButton(
                onClick = {},
                text = "Login",
                loading = true,
                enabled = false
            )
        }
    }
}