package ge.tba.city_park.reservation.presentation.screen.reservations

sealed interface ReservationsEvent {
    data object AddReservationButtonClicked : ReservationsEvent
    data object Refresh : ReservationsEvent
}