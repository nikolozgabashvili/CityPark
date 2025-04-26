package ge.tbca.city_park.payment.domain.usecase

import ge.tbca.city_park.payment.domain.model.CardType
import javax.inject.Inject

class GetCardTypeUseCase @Inject constructor() {

    operator fun invoke(cardNumber: String): CardType {
        val trimmed = cardNumber.trim()

        val firstTwoDigits = trimmed.take(2).toIntOrNull()
        val firstFourDigits = trimmed.take(4).toIntOrNull()
        return when {
            trimmed.startsWith("4") -> CardType.VISA
            firstTwoDigits in 51..55 -> CardType.MASTERCARD
            firstFourDigits in 2221..2720 -> CardType.MASTERCARD
            else -> CardType.OTHER
        }
    }
}