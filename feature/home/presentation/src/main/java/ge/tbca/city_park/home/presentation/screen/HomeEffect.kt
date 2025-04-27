package ge.tbca.city_park.home.presentation.screen

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface HomeEffect {
    data class Error(val error: GenericString) : HomeEffect
    data object NavigateToAddBalance : HomeEffect
    data object NavigateToProfile : HomeEffect
    data object NavigateToCars : HomeEffect
    data object NavigateToCards : HomeEffect
    data object NavigateToAddReservation : HomeEffect
}