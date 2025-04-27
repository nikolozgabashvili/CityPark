package ge.tbca.city_park.home.presentation.screen

sealed interface HomeScreenEvent {
    data object Refresh : HomeScreenEvent
    data object NavigateToAddBalance : HomeScreenEvent
    data object NavigateToProfile : HomeScreenEvent
    data object NavigateToAddReservation : HomeScreenEvent
    data object NavigateToCars : HomeScreenEvent
    data object NavigateToCards : HomeScreenEvent
    data object OnFinishRequest : HomeScreenEvent
    data object DismissParkingDialog : HomeScreenEvent
    data object FinishParking : HomeScreenEvent

}