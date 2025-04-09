package ge.tbca.city_park.presentation.features.change_password.screen

sealed interface ChangePasswordEffect {
    data object NavigateBack : ChangePasswordEffect
}