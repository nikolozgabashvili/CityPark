package com.example.core.designsystem.components.top_navigation_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview

@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    startIcon: ImageVector? = null,
    onStartIconClick: () -> Unit = {},
    endIcon: ImageVector? = null,
    onEndIconClick: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxWidth().height(Dimen.size40)) {
        title?.let { title ->
            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = title,
                color = AppColors.primary,
                style = TextStyles.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        startIcon?.let { startIcon ->
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
