package ge.tbca.city_park.auth.presentation.screen.change_password

import ge.tbca.city_park.auth.domain.model.PasswordValidationState

data class ChangePasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val repeatNewPassword: String = "",
    val showNewPasswordError: Boolean = false,
    val showRepeatPasswordError: Boolean = false,
    val isOldPasswordVisible: Boolean = false,
    val isNewPasswordVisible: Boolean = false,
    val isRepeatNewPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState()
) {
    val showPasswordRequirements: Boolean
        get() = newPassword.isNotEmpty() && !passwordValidationState.isValid
}