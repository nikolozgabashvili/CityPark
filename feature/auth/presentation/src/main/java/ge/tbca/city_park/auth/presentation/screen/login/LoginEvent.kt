package ge.tbca.city_park.auth.presentation.screen.login

sealed interface LoginEvent {
    data object LoginButtonClicked : LoginEvent
    data object GoogleLoginButtonClicked : LoginEvent
    data object PasswordRecoveryClicked : LoginEvent
    data object RegisterHereClicked : LoginEvent
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data object PasswordVisibilityChanged : LoginEvent
}
