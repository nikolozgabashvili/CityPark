package ge.tba.city_park.reservation.presentation.screen.create_reservation

sealed interface CreateReservationEvent {
    data object BackButtonClicked : CreateReservationEvent
    data object ChooseOnMapButtonClicked : CreateReservationEvent
    data object CloseBottomSheet : CreateReservationEvent
    data object ShowBottomSheet : CreateReservationEvent
    data object Retry : CreateReservationEvent
    data object NavigateToAddCar : CreateReservationEvent
    data class CarSelected(val id: Int) : CreateReservationEvent
    data object CreateReservationButtonClicked : CreateReservationEvent
    data class ZoneCodeChanged(val zoneCode: String) : CreateReservationEvent
}