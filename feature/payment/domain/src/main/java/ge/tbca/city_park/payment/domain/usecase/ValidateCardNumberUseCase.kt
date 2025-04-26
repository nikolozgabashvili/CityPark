package ge.tbca.city_park.payment.domain.usecase

import javax.inject.Inject

class ValidateCardNumberUseCase @Inject constructor() {

    operator fun invoke(cardNumber: String): Boolean {
        val trimmed = cardNumber.trim()
        return trimmed.length == 16 && trimmed.all { it.isDigit() }
    }
}