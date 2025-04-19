package ge.tbca.city_park.auth.domain.usecase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    private val hasSpecialCharUseCase: CheckSpecialCharUseCase
) {

    operator fun invoke(password: String): ge.tbca.city_park.auth.domain.model.PasswordValidationState {
        val hasMinLength = password.length >= PASSWORD_MIN_LENGTH
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = hasSpecialCharUseCase(password)


        val validationState = ge.tbca.city_park.auth.domain.model.PasswordValidationState(
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