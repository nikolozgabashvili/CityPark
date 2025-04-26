package ge.tbca.city_park.cars.presentation.screen.add_car

data class AddCarState(
    val carName: String = "",
    val plateNumber: String = "",
    val ownerPersonalNumber: String = "",
    val isChecked: Boolean = false,
    val showCarNameError: Boolean = false,
    val showPlateNumberError: Boolean = false,
    val showOwnerPersonalNumberError: Boolean = false,
    val isLoading: Boolean = false
)