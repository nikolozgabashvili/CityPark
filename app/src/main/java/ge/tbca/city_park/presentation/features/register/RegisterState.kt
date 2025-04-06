package ge.tbca.city_park.presentation.features.register

import ge.tbca.city_park.presentation.core.model.PasswordValidationState

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isRepeatPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState()
) {
    val showPasswordValidations: Boolean
        get() = password.isNotEmpty() && !passwordValidationState.isValid
}