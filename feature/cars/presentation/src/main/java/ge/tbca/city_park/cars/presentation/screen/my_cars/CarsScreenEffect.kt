package ge.tbca.city_park.cars.presentation.screen.my_cars

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface CarsScreenEffect {
    data object NavigateToAddCar : CarsScreenEffect
    data object NavigateBack : CarsScreenEffect
    data class Error(val error: GenericString) : CarsScreenEffect
}