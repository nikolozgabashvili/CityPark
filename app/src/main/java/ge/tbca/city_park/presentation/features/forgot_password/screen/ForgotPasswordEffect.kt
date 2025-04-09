package ge.tbca.city_park.presentation.features.forgot_password.screen

sealed interface ForgotPasswordEffect {
    data object NavigateBack : ForgotPasswordEffect
}