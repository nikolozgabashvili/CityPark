package ge.tbca.city_park.cars.presentation.extension

import ge.tbca.citi_park.core.ui.util.GenericString
import ge.tbca.city_park.cars.domain.error.CarError
import ge.tbca.city_park.cars.presentation.R

fun CarError.toGenericString(): GenericString {
    val stringRes = when (this) {
        // TODO enums
        CarError.OTHER -> R.string.empty
    }
    return GenericString.StringResource(stringRes)
}