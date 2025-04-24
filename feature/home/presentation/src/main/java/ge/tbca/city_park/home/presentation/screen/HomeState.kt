package ge.tbca.city_park.home.presentation.screen

import ge.tbca.city_park.cars.presentation.model.CarUi

data class HomeState(
    val cars: List<CarUi> = emptyList(),
    val isLoading: Boolean = false
)