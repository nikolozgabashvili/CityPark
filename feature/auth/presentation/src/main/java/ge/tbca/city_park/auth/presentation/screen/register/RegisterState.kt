package ge.tbca.city_park.auth.presentation.screen.register

import ge.tbca.city_park.auth.domain.model.PasswordValidationState

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val showPasswordError: Boolean = false,
    val showEmailError: Boolean = false,
    val showRepeatPasswordError: Boolean = false,
    val isRepeatPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState()
) {
    val showPasswordRequirements: Boolean
        get() = password.isNotEmpty() && !passwordValidationState.isValid
}