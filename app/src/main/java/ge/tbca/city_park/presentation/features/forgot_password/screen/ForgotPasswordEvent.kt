package ge.tbca.city_park.presentation.features.forgot_password.screen

sealed interface ForgotPasswordEvent {
    data class EmailChanged(val email: String) : ForgotPasswordEvent
    data object SubmitButtonClicked : ForgotPasswordEvent
    data object BackButtonClicked : ForgotPasswordEvent
}
