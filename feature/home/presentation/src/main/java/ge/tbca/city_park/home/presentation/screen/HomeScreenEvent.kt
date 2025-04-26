package ge.tbca.city_park.home.presentation.screen

sealed interface HomeScreenEvent {
    data object Refresh : HomeScreenEvent
    data object NavigateToAddBalance : HomeScreenEvent
    data object NavigateToProfile : HomeScreenEvent

}