package ge.tbca.city_park.payment.domain.usecase

import javax.inject.Inject

class ValidateCardNumberUseCase @Inject constructor() {

    operator fun invoke(cardNumber: String): Boolean {
        cardNumber.forEach {
            if (!it.isDigit()) return false
        }
        return cardNumber.length == 16
    }
}