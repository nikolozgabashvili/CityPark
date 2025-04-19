package ge.tbca.city_park.auth.presentation.screen.recover_password

sealed interface RecoveryPasswordEvent {
    data class EmailChanged(val email: String) : RecoveryPasswordEvent
    data object SubmitButtonClicked : RecoveryPasswordEvent
    data object BackButtonClicked : RecoveryPasswordEvent
}
