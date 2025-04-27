package ge.tbca.city_park.more.presentation.screen

sealed interface MoreEffect {
    data object NavigateToSettings: MoreEffect
    data object NavigateToCars: MoreEffect
    data object NavigateToCards: MoreEffect
    data object NavigateToProfile: MoreEffect
}