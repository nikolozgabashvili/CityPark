package ge.tbca.city_park.presentation.core.design_system.components.horizontal_panel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ge.tbca.city_park.presentation.core.design_system.components.button.radio_button.PrimaryRadioButton
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun HorizontalPanel(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    startIcon: @Composable () -> Unit,
    endIcon: @Composable (() -> Unit)? = null,
    hasUnderLine: Boolean = false,
) {
    CompositionLocalProvider(LocalContentColor provides AppColors.primary) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick, indication = null, interactionSource = null),
                verticalAlignment = Alignment.CenterVertically
            ) {

                startIcon()
                Spacer(modifier = Modifier.width(Dimen.size8))


                Text(
                    style = AppTypography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.primary,
                    text = title
                )
                Spacer(modifier = Modifier.weight(1f))

                description?.let {
                    Text(
                        style = AppTypography.bodySmall,
                        color = AppColors.secondary,
                        text = it
                    )
                }

                Spacer(modifier = Modifier.width(Dimen.size16))

                endIcon?.invoke()
            }

            if (hasUnderLine) {
                Spacer(modifier = Modifier.height(Dimen.size6))
                Divider(modifier = Modifier.padding(start = Dimen.size48))
            }

        }

    }
}


@Composable
@AppPreview
private fun SettingsItemPreview() {
    AppTheme {

        HorizontalPanel(
            startIcon = {
                PrimaryRadioButton(
                    onClick = {},
                    isSelected = false
                )
            },

            endIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                    contentDescription = null,
                )
            },
            hasUnderLine = true,
            title = "title",
            description = "adhakjshdgja",
            onClick = {}
        )

    }
}