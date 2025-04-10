package ge.tbca.city_park.presentation.core.design_system.components.list.settings_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    settingsDetail: SettingsDetail
) {
    Box(
        modifier = modifier.clickable(onClick = { onClick(settingsDetail.id) }),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = settingsDetail.startIcon,
                contentDescription = null,
                tint = AppColors.primary
            )

            Spacer(modifier = Modifier.width(Dimen.size16))

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                style = AppTypography.bodyMedium,
                color = AppColors.primary,
                text = settingsDetail.name
            )

            Spacer(modifier = Modifier.weight(1f))

            settingsDetail.endText?.let {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = AppTypography.bodyMedium,
                    color = AppColors.secondary,
                    text = it
                )
            }

            Spacer(modifier = Modifier.width(Dimen.size16))

            Icon(
                modifier = Modifier
                    .size(Dimen.size20)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                contentDescription = null,
                tint = AppColors.secondary
            )
        }

        Spacer(modifier = Modifier.height(Dimen.size50))

        Divider(
            modifier = Modifier
                .padding(start = Dimen.size32)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
@AppPreview
private fun SettingsItemPreview() {
    AppTheme {
        SettingsItem(
            onClick = {},
            settingsDetail = SettingsDetail(
                id = "language",
                name = "ენა",
                startIcon = Icons.Default.Language,
                endText = "ქართული"
            )
        )
    }
}