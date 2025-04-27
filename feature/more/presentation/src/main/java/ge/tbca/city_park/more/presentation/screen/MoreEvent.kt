package ge.tbca.city_park.more.presentation.screen

sealed interface MoreEvent {
    data object NavigateToSettings: MoreEvent
    data object NavigateToCars: MoreEvent
    data object NavigateToCards: MoreEvent
    data object NavigateToProfile: MoreEvent
}