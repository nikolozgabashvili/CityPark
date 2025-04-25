package ge.tbca.city_park.home.presentation.screen

import ge.tbca.citi_park.core.ui.util.GenericString
import ge.tbca.city_park.cars.presentation.model.CarUi

data class HomeState(
    val cars: List<CarUi> = emptyList(),
    val isLoading: Boolean = false,
    val carsLoading:Boolean = false,
    val isRefreshing: Boolean = false,
    val noCars:Boolean = false,
    val error: GenericString? = null,
)