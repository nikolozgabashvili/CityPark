package ge.tbca.city_park.payment.domain.usecase

import javax.inject.Inject

class ValidateCardNumberUseCase @Inject constructor() {

    operator fun invoke(cardNumber: String) : Boolean {
        return cardNumber.length == 16
    }
}