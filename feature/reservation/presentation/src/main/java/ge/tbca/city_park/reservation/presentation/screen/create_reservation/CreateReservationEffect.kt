package ge.tbca.city_park.reservation.presentation.screen.create_reservation

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface CreateReservationEffect {
    data object Success : CreateReservationEffect
    data object NoCarSelected : CreateReservationEffect
    data class Error(val error: GenericString) : CreateReservationEffect
    data object NavigateBack : CreateReservationEffect
    data object NavigateToMap : CreateReservationEffect
    data object NavigateToAddCar : CreateReservationEffect
    data object NavigateToAddBalance : CreateReservationEffect
}