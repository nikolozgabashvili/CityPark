package ge.tbca.city_park.presentation.core.design_system.components.button.extension

import androidx.compose.material3.ButtonColors
import ge.tbca.city_park.R

/**
 * Returns the appropriate loader resource ID based on the button's enabled state and colors.
 *
 * @param enabled Boolean indicating if the button is enabled.
 *  [ButtonColors] object containing the button's color information.
 * @return Resource ID of the loader to be used.
 */
fun ButtonColors.loaderResourceId(enabled: Boolean): Int {
    val containerColor = if (enabled) this.containerColor else this.disabledContainerColor

    /** Calculate the perceived brightness of the container color using the luminance formula
     * @see <a href="https://en.wikipedia.org/wiki/Relative_luminance">luminance formula</a>
     *  */
    val perceivedColor = containerColor.red * 0.299f +
            containerColor.green * 0.587f +
            containerColor.blue * 0.114f

    return if (perceivedColor > 0.5) R.raw.button_loader_tertiary else R.raw.button_loader
}