package ge.tbca.city_park.presentation.core.design_system.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun IconWithBackground(
    modifier: Modifier = Modifier,
    icon: ImageVector
) {
    Box(
        modifier = modifier.background(AppColors.secondaryContainer, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.padding(Dimen.size12),
            imageVector = icon,
            tint = AppColors.onSecondaryContainer,
            contentDescription = null
        )


    }
}

@AppPreview
@Composable
private fun IconWithBackgroundPrev() {
    AppTheme {
        IconWithBackground(
            modifier = Modifier.size(Dimen.size40),
            icon = Icons.Rounded.Language
        )

    }
}