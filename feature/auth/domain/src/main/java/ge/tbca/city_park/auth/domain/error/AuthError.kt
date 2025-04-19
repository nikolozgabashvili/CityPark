package ge.tbca.city_park.auth.domain.error

import ge.tbca.city_park.core.domain.util.ResourceError

enum class AuthError : ResourceError {
    UNKNOWN,
    INVALID_CREDENTIALS,
    NETWORK_ERROR,
    INVALID_EMAIL_FORMAT,
    WRONG_OLD_PASSWORD,
    USER_COLLISION
}