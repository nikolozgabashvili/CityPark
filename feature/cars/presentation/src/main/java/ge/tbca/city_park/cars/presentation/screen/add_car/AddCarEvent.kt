package ge.tbca.city_park.cars.presentation.screen.add_car

sealed interface AddCarEvent {
    data object SaveCarButtonClicked : AddCarEvent
    data object BackButtonClicked : AddCarEvent
    data class CarNameChanged(val carName: String) : AddCarEvent
    data class PlateNumberChanged(val plateNumber: String) : AddCarEvent
    data class OwnerPersonalNumberChanged(val ownerPersonalNumber: String) : AddCarEvent
    data object CheckBoxChanged : AddCarEvent
}