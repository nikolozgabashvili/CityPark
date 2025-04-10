package ge.tbca.city_park.domain.core.util

enum class NetworkError : ResourceError {
    UNKNOWN,
    INVALID_CREDENTIALS,
    NETWORK_ERROR,
    USER_COLLISION
}