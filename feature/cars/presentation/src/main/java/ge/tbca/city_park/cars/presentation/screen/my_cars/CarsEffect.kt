package ge.tbca.city_park.cars.presentation.screen.my_cars

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface CarsEffect {
    data object NavigateToAddCar : CarsEffect
    data object NavigateBack : CarsEffect
    data class Error(val error: GenericString) : CarsEffect
}