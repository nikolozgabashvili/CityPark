package ge.tbca.city_park.payment.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.model.CardRequest
import ge.tbca.city_park.payment.domain.model.CreditCard
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCreditCardUseCase @Inject constructor(
    private val creditCardRepository: CreditCardRepository,
    private val getCardTypeUseCase: GetCardTypeUseCase
) {

    operator fun invoke(
        cardNumber: String,
        holderName: String,
        expirationMonth: Int,
        expirationYear: Int,
        cvv: String,
    ): Flow<Resource<CreditCard, ApiError>> {

        val cardType = getCardTypeUseCase(cardNumber)

        return creditCardRepository.addCreditCard(
            CardRequest(
                cardNumber = cardNumber,
                holderName = holderName,
                expirationMonth = expirationMonth,
                expirationYear = expirationYear,
                cvv = cvv,
                cardType = cardType.name
            )
        )
    }
}