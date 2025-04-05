package ge.tbca.city_park.presentation.features.login

sealed class LoginEvent {
    data object LoginButtonClicked : LoginEvent()
    data object ForgotPasswordClicked : LoginEvent()
    data object RegisterHereClicked : LoginEvent()
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object PasswordVisibilityChanged : LoginEvent()
}
