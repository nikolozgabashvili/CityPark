package com.example.core.designsystem.components.bottom_nav_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.AppTypography
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                indication = ripple(
                    color = LocalContentColor.current,
                    bounded = true,

                ),
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(vertical = Dimen.size12),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (selected) selectedIcon else unselectedIcon,
            contentDescription = label,
            tint = if (selected) AppColors.primary else AppColors.onSurfaceVariant,
            modifier = Modifier.size(Dimen.size28)
        )

        Spacer(modifier = Modifier.height(Dimen.sizeSmall))

        Text(
            text = label,
            fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.Medium,
            style = AppTypography.labelSmall,
            color = if (selected) AppColors.primary else AppColors.onSurfaceVariant
        )
    }
}

@AppPreview
@Composable
private fun BottomNavItemPreviewSelected() {
    AppTheme {
        BottomNavItem(
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            label = "Settings",
            selected = true,
            onClick = {}
        )
    }
}

@AppPreview
@Composable
private fun BottomNavItemPreviewUnselected() {
    AppTheme {
        BottomNavItem(
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            label = "Settings",
            selected = false,
            onClick = {}
        )
    }
}