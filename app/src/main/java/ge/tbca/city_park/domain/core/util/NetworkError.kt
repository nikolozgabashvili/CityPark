package ge.tbca.city_park.domain.core.util

enum class NetworkError : ResourceError {
    UNKNOWN,
    INVALID_CREDENTIALS,
    NETWORK_ERROR,
    INVALID_EMAIL_FORMAT,
    WRONG_OLD_PASSWORD,
    USER_COLLISION
}