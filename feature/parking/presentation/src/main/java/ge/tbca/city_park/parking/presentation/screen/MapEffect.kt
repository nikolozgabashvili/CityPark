package ge.tbca.city_park.parking.presentation.screen

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface MapEffect {
    data class Error(val error: GenericString) : MapEffect

    data object NavigateToAddBalance : MapEffect
    data object NavigateToAddCar : MapEffect
    data object ReservationCreated : MapEffect
}