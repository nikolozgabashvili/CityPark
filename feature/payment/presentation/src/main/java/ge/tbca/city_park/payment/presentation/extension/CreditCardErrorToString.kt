package ge.tbca.city_park.payment.presentation.extension

import ge.tbca.citi_park.core.ui.util.GenericString
import ge.tbca.city_park.payment.domain.error.CreditCardError
import ge.tbca.city_park.payment.presentation.R

fun CreditCardError.toGenericString() : GenericString {
    val stringRes = when(this) {
        // TODO enums
        CreditCardError.OTHER -> R.string.empty
    }
    return GenericString.StringResource(stringRes)
}