package ge.tbca.city_park.cars.presentation.screen.my_cars

import ge.tbca.city_park.core.ui.util.GenericString
import ge.tbca.city_park.cars.presentation.model.CarUi

data class CarsState(
    val cars: List<CarUi> = emptyList(),
    val carsLoading: Boolean = false,
    val noCars: Boolean = false,
    val error: GenericString? = null,
    val showDeleteCarDialog: Boolean = false,
    val deleteCarId: Int? = null
)