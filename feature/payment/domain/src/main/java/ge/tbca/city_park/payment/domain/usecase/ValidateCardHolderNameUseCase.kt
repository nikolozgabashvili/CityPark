package ge.tbca.city_park.payment.domain.usecase

import javax.inject.Inject

class ValidateCardHolderNameUseCase @Inject constructor() {

    operator fun invoke(cardHolderName: String): Boolean {
        val trimmed = cardHolderName.trim().replace(Regex("\\s+"), " ")
        val regex = "^[A-Za-z ]{5,30}$".toRegex()
        return regex.matches(trimmed)
    }
}