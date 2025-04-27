package ge.tbca.city_park.home.presentation.screen

sealed interface HomeEvent {
    data object Refresh : HomeEvent
    data object NavigateToAddBalance : HomeEvent
    data object NavigateToProfile : HomeEvent
}