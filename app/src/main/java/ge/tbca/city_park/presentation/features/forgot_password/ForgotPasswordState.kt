package ge.tbca.city_park.presentation.features.forgot_password

data class ForgotPasswordState(
    val email: String = "",
    val showEmailError: Boolean = false,
    val isLoading: Boolean = false
)