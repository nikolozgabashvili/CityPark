package com.example.core.designsystem.components.button.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.core.designsystem.components.button.extension.loaderResourceId
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview

@Composable
fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    loading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    enabled: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    val isEnabled = enabled && !loading

    val buttonParams = buttonSize.getButtonParams()
    val loaderRes = colors.loaderResourceId(isEnabled)

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(loaderRes)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = loading,
        iterations = Int.MAX_VALUE
    )

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

            Box(modifier = Modifier.alpha(contentAlpha)) {
                content()
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
                enabled = true
            ) {}

            Spacer(modifier = Modifier.height(Dimen.sizeSmall))

            BaseButton(
                onClick = {},
                buttonSize = ButtonSize.MEDIUM,
                enabled = true
            ) {}
            Spacer(modifier = Modifier.height(Dimen.sizeSmall))

            BaseButton(
                onClick = {},
                buttonSize = ButtonSize.SMALL,
                enabled = true
            ) {}
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
                loading = true,
            ) {}
        }
    }
}