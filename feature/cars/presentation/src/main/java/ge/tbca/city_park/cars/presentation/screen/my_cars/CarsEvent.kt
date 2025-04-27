package ge.tbca.city_park.cars.presentation.screen.my_cars

sealed interface CarsEvent {
    data object AddCarButtonClicked : CarsEvent
    data object Refresh : CarsEvent
    data object BackButtonClicked : CarsEvent
    data object DismissDeleteCarDialog : CarsEvent
    data object DeleteCar : CarsEvent
    data class DeleteCarClicked(val carId: Int) : CarsEvent
}