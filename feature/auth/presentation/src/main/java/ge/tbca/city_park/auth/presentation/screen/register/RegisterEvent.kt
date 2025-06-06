package ge.tbca.city_park.auth.presentation.screen.register

sealed interface RegisterEvent {
    data object RegisterButtonClicked : RegisterEvent
    data object BackButtonClicked : RegisterEvent
    data class EmailChanged(val email: String) : RegisterEvent
    data class PasswordChanged(val password: String) : RegisterEvent
    data class RepeatPasswordChanged(val repeatPassword: String) : RegisterEvent
    data object PasswordVisibilityChanged : RegisterEvent
    data object RepeatPasswordVisibilityChanged : RegisterEvent
}
