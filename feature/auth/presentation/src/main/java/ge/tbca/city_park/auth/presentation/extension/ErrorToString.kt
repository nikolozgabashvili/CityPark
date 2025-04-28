package ge.tbca.city_park.auth.presentation.extension

import ge.tbca.city_park.core.ui.util.GenericString
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.auth.presentation.R


fun AuthError.toGenericString(): GenericString {
    val stringRes = when (this) {
        AuthError.UNKNOWN -> R.string.unknown_error
        AuthError.INVALID_CREDENTIALS -> R.string.invalid_credentials
        AuthError.NETWORK_ERROR -> R.string.network_error
        AuthError.USER_COLLISION -> R.string.user_already_exists
        AuthError.INVALID_EMAIL_FORMAT -> R.string.invalid_email_format
        AuthError.WRONG_OLD_PASSWORD -> R.string.old_password_is_incorrect
    }
    return GenericString.StringResource(stringRes)
}