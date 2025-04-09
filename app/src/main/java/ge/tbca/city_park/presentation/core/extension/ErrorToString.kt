package ge.tbca.city_park.presentation.core.extension

import ge.tbca.city_park.R
import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.presentation.core.util.GenericString

fun NetworkError.toGenericString(): GenericString {
    val stringRes = when (this) {
        NetworkError.UNKNOWN -> R.string.unknown_error
        NetworkError.INVALID_CREDENTIALS -> R.string.invalid_credentials
        NetworkError.INVALID_EMAIL_FORMAT -> R.string.invalid_email
        NetworkError.NETWORK_ERROR -> R.string.network_error
        NetworkError.USER_COLLISION -> R.string.user_already_exists
    }
    return GenericString.StringResource(stringRes)
}