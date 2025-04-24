package ge.tbca.city_park.home.presentation.screen

sealed interface HomeEvent {
    data object AddCarButtonClicked : HomeEvent
    data class CarClicked(val carId: Int) : HomeEvent
}