package com.example.core.designsystem.components.action_card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import com.example.core.designsystem.util.shimmer

@Composable
fun ActionCard(
    modifier: Modifier = Modifier,
    text: String,
    loading: Boolean = false,
    enabled: Boolean = true,
    startIcon: ImageVector? = null,
    onclick: () -> Unit,
) {

    val isEnabled = if (loading) false else enabled

    Surface(
        modifier = modifier.shimmer(loading),
        onClick = onclick,
        enabled = isEnabled,
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimen.size12, vertical = Dimen.size16),
            contentAlignment = Alignment.Center
        ) {
            if (startIcon != null) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = Dimen.size16),
                    imageVector = startIcon,
                    contentDescription = null,
                    tint = AppColors.primary
                )
            }

            Text(
                modifier = Modifier.alpha(if (loading) 0f else 1f),
                text = text,
                style = TextStyles.titleMedium,
                fontWeight = FontWeight.Bold,
                color = AppColors.primary
            )
        }
    }
}


@AppPreview
@Composable
private fun ActionCardPreview() {
    AppTheme {
        ActionCard(
            modifier = Modifier.fillMaxWidth(),
            text = "Action Card",
            onclick = {}
        )
    }
}

@AppPreview
@Composable
private fun ActionCardPreviewWithIcon() {
    AppTheme {
        ActionCard(
            modifier = Modifier.fillMaxWidth(),
            text = "Action Card",
            startIcon = Icons.Default.LocalParking,
            onclick = {}
        )
    }
}