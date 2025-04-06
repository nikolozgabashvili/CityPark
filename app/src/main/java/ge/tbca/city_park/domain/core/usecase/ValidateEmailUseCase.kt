package ge.tbca.city_park.domain.core.usecase

import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): Boolean {
        return email.matches(VALIDATION_REGEX_EMAIL.toRegex())

    }

    companion object {
        private const val VALIDATION_REGEX_EMAIL =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    }
}