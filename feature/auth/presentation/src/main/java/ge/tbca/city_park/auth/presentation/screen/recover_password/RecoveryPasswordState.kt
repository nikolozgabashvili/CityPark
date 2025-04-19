package ge.tbca.city_park.auth.presentation.screen.recover_password

data class RecoveryPasswordState(
    val email: String = "",
    val showEmailError: Boolean = false,
    val isLoading: Boolean = false
)