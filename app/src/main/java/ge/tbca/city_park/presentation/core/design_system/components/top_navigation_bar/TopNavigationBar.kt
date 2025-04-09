package ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun TopNavigationBar(
    title: String? = null,
    startIcon: ImageVector? = null,
    onStartIconClick: () -> Unit = {},
    endIcon: ImageVector? = null,
    onEndIconClick: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        title?.let { title ->
            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = title,
                color = AppColors.primary,
                style = AppTypography.titleMedium
            )
        }

        startIcon?.let { startIcon ->
            // TODO replace with custom component
            IconButton(
                onClick = onStartIconClick
            ) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = null,
                    tint = AppColors.primary
                )
            }
        }

        endIcon?.let { endIcon ->
            // TODO replace with custom component
            IconButton(
                modifier = Modifier.align(alignment = Alignment.CenterEnd),
                onClick = onEndIconClick
            ) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = null,
                    tint = AppColors.primary
                )
            }
        }
    }
}

@Composable
@AppPreview
private fun ScreenHeaderPreview() {
    AppTheme {
        TopNavigationBar(
            title = "Screen Title Test",
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack
        )
    }
}
