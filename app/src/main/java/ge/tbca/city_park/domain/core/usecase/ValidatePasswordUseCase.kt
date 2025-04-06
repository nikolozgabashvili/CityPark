package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.presentation.core.model.PasswordValidationState
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    private val hasSpecialCharUseCase: CheckSpecialCharUseCase
) {

    operator fun invoke(password: String): PasswordValidationState {
        val hasMinLength = password.length >= 8
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = hasSpecialCharUseCase(password)


        val validationState = PasswordValidationState(
            hasMinLength = hasMinLength,
            hasUpperCase = hasUpperCase,
            hasLowerCase = hasLowerCase,
            hasDigit = hasDigit,
            hasSpecialChar = hasSpecialChar
        )
        return validationState
    }

    companion object {
        private const val PASSWORD_MIN_LENGTH = 8
    }
}