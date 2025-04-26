package ge.tba.city_park.reservation.presentation.screen.create_reservation

import ge.tbca.city_park.cars.presentation.model.CarUi

data class CreateReservationState(
    val carsList: List<CarUi> = emptyList(),
    val zoneCode: String = "",
    val selectedCarId: Int? = null,
    val showDropDown: Boolean = false,
    val showZoneCodeError: Boolean = false,
    val isLoading: Boolean = false
) {
    val selectedCar: CarUi?
        get() = carsList.firstOrNull { it.id == selectedCarId }
}