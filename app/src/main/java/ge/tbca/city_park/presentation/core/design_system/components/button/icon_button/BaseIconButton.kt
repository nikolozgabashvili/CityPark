package ge.tbca.city_park.presentation.core.design_system.components.button.icon_button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tbca.city_park.presentation.core.design_system.components.button.base.BaseButton
import ge.tbca.city_park.presentation.core.design_system.components.button.base.ButtonSize

@Composable
fun BaseIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    loading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    enabled: Boolean = true,
) {

    val buttonParams = buttonSize.getButtonParams()

    BaseButton(
        modifier = modifier,
        onClick = onClick,
        buttonSize = buttonSize,
        loading = loading,
        colors = colors,
        enabled = enabled,
    ) {
        Image(
            modifier = Modifier.size(buttonParams.iconSize),
            imageVector = icon,
            contentDescription = null
        )

    }

}
