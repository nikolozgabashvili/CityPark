package ge.tbca.city_park.user.presentation.screen

sealed interface ProfileEvent {
    data object BackButtonClicked : ProfileEvent
    data object ChangePasswordButtonClicked : ProfileEvent
    data object SignOutButtonClicked : ProfileEvent
    data object DismissActiveReservationDialog: ProfileEvent
    data object Refresh: ProfileEvent
    data object FinishParking: ProfileEvent
}