package ge.tbca.city_park.home.presentation.screen

sealed interface HomeEvent {
    data object Refresh : HomeEvent
    data object NavigateToAddBalance : HomeEvent
    data object NavigateToProfile : HomeEvent
    data object NavigateToAddReservation : HomeEvent
    data object NavigateToCars : HomeEvent
    data object NavigateToCards : HomeEvent
    data object OnFinishRequest : HomeEvent
    data object DismissParkingDialog : HomeEvent
    data object FinishParking : HomeEvent
}