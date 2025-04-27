package ge.tbca.city_park.home.presentation.screen

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface HomeScreenEffect {
    data class Error(val error: GenericString) : HomeScreenEffect
    data object NavigateToAddBalance : HomeScreenEffect
    data object NavigateToProfile : HomeScreenEffect
    data object NavigateToCars : HomeScreenEffect
    data object NavigateToCards : HomeScreenEffect
    data object NavigateToAddReservation : HomeScreenEffect
}