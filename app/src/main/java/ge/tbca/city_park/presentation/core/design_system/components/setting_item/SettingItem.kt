package ge.tbca.city_park.presentation.core.design_system.components.setting_item

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.core.design_system.components.horizontal_panel.HorizontalPanel
import ge.tbca.city_park.presentation.core.design_system.components.image.IconWithBackground
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun SettingItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    hasUnderline: Boolean = false,
    icon: ImageVector
) {
    HorizontalPanel(
        modifier = modifier,
        title = title,
        description = description,
        hasUnderline = hasUnderline,
        startIcon = {
            IconWithBackground(
                modifier = Modifier.size(Dimen.size40),
                icon = icon
            )
        },
        endIcon = {
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = null
            )
        },
        onClick = onClick
    )
}

@AppPreview
@Composable
private fun SettingItemPrev() {
    AppTheme {
        SettingItem(
            title = "title",
            icon = Icons.Rounded.Language,
            onClick = {}
        )
    }

}