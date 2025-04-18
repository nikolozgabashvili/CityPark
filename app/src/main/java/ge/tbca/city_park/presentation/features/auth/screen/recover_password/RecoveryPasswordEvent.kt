package ge.tbca.city_park.presentation.features.auth.screen.recover_password

sealed interface RecoveryPasswordEvent {
    data class EmailChanged(val email: String) : RecoveryPasswordEvent
    data object SubmitButtonClicked : RecoveryPasswordEvent
    data object BackButtonClicked : RecoveryPasswordEvent
}
