package ge.tbca.city_park.cars.presentation.screen.add_car

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface AddCarEffect {
    data object NavigateBack : AddCarEffect
    data object Success : AddCarEffect
    data class ShowSnackbar(val message: GenericString) : AddCarEffect
}