package ge.tbca.city_park.cars.presentation.screen.my_cars

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface CarsScreenEventEffect {
    data object NavigateToAddCar : CarsScreenEventEffect
    data object NavigateBack : CarsScreenEventEffect

    data class Error(val error :GenericString) : CarsScreenEventEffect
}