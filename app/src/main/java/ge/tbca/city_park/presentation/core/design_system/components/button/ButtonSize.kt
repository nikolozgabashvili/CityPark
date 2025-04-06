package ge.tbca.city_park.presentation.core.design_system.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.theme.TextStyles

enum class ButtonSize {
    LARGE,
    MEDIUM,
    SMALL;

    @Composable
    fun getButtonParams(): ButtonStyleParams {
        return when (this) {
            LARGE -> ButtonStyleParams(
                buttonHeight = Dimen.buttonLarge,
                iconSize = Dimen.iconLarge,
                textStyle = TextStyles.titleMedium,
                inPadding = Dimen.buttonPaddingInLarge,
                outPadding = Dimen.buttonPaddingOutLarge
            )

            MEDIUM -> ButtonStyleParams(
                buttonHeight = Dimen.buttonMedium,
                iconSize = Dimen.iconMedium,
                textStyle = TextStyles.titleSmall,
                inPadding = Dimen.buttonPaddingInMedium,
                outPadding = Dimen.buttonPaddingOutMedium
            )

            SMALL -> ButtonStyleParams(
                buttonHeight = Dimen.buttonSmall,
                iconSize = Dimen.iconSmall,
                textStyle = TextStyles.labelMedium,
                inPadding = Dimen.buttonPaddingInSmall,
                outPadding = Dimen.buttonPaddingOutMedium
            )
        }
    }

}


data class ButtonStyleParams(
    val buttonHeight: Dp,
    val iconSize: Dp,
    val textStyle: TextStyle,
    val inPadding: Dp,
    val outPadding:Dp
)