package com.example.core.designsystem.components.error_wrapper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.designsystem.R

@Composable
fun ErrorWrapper(
    modifier: Modifier = Modifier,
    error: String,
    enabled:Boolean,
    onRetry: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                AppColors.surface, shape = RoundedCornerShape(
                    Dimen.roundedCornerMediumSize
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.size12),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = error, color = AppColors.onSurface, style = TextStyles.bodyMedium)
            Spacer(modifier=Modifier.height(Dimen.size20))
            PrimaryButton(
                enabled = enabled,
                startIcon = Icons.Rounded.Refresh,
                onClick = onRetry,
                text = stringResource(R.string.retry),
                buttonSize = ButtonSize.SMALL,
            )

        }


    }

}


@AppPreview
@Composable
private fun ErrorWrapperPrev() {
    AppTheme {
        ErrorWrapper(
            error = "Error",
            onRetry = {},
            enabled = true
        )
    }


}