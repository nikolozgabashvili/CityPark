package ge.tbca.city_park.cars.presentation.screen.my_cars

sealed interface CarsScreenEvent {
    data object AddCarButtonClicked : CarsScreenEvent
    data object Refresh : CarsScreenEvent
    data object BackButtonClicked : CarsScreenEvent

    data class CarClicked(val carId: Int) : CarsScreenEvent
}