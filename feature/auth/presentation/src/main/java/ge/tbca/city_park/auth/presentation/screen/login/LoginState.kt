package ge.tbca.city_park.auth.presentation.screen.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val showPasswordError: Boolean = false,
    val showEmailError: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false
)
