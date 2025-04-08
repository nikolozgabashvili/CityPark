package ge.tbca.city_park.presentation.features.forgot_password

sealed interface ForgotPasswordEffect {
    data object NavigateBack : ForgotPasswordEffect
}