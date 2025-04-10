package ge.tbca.city_park.presentation.features.auth.screen.change_password

sealed interface ChangePasswordEffect {
    data object NavigateBack : ChangePasswordEffect
}