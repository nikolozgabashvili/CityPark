package ge.tbca.city_park.core.ui.mapper

import ge.tbca.city_park.core.ui.util.GenericString
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.ui.R

fun ApiError.toGenericString():GenericString{
    val errorRes =  when (this){
        ApiError.UNAUTHORIZED -> R.string.unknown_error
        ApiError.UNKNOWN_ERROR -> R.string.unknown_error
        ApiError.INTERNAL_SERVER_ERROR -> R.string.internal_server_error
        ApiError.INVALID_REQUEST -> R.string.unknown_error
        ApiError.MISSING_USER_ID -> R.string.unknown_error
        ApiError.PARKING_SPOT_NOT_FOUND -> R.string.parking_spot_not_found
        ApiError.NO_AVAILABLE_SPOTS -> R.string.no_available_spots
        ApiError.INVALID_ID_FORMAT -> R.string.unknown_error
        ApiError.RESERVATION_NOT_FOUND -> R.string.unknown_error
        ApiError.CAR_ALREADY_RESERVED -> R.string.car_already_reserved
        ApiError.USER_ALREADY_HAS_RESERVATION -> R.string.user_already_has_reservation
        ApiError.USER_NOT_FOUND -> R.string.unknown_error
        ApiError.CARD_NOT_FOUND -> R.string.unknown_error
        ApiError.CAR_NOT_FOUND -> R.string.unknown_error
        ApiError.CAR_ALREADY_EXISTS -> R.string.car_already_exists
        ApiError.CARD_ALREADY_EXISTS -> R.string.card_already_exists
        ApiError.CARD_EXPIRED -> R.string.card_expired
        ApiError.INSUFFICIENT_BALANCE -> R.string.insufficient_balance
        ApiError.NETWORK_ERROR -> R.string.network_error
    }

    return GenericString.StringResource(errorRes)
}