package ge.tbca.city_park.payment.domain.usecase

import javax.inject.Inject

class ValidateCardCvvUseCase @Inject constructor() {
    operator fun invoke(cvv: String): Boolean {
        val trimmed = cvv.trim()
        val regex = "^[0-9]{3}$".toRegex()
        return regex.matches(trimmed)
    }
}