package ge.tbca.city_park.presentation.features.login.screen

sealed interface LoginEffect {
    data object NavigateToRegister : LoginEffect
    data object NavigateToForgotPassword : LoginEffect
}