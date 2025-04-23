package ge.tbca.city_park.payment.domain.usecase

import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.error.CreditCardError
import ge.tbca.city_park.payment.domain.model.CreditCard
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCreditCardUseCase @Inject constructor(private val creditCardRepository: CreditCardRepository) {

    operator fun invoke(creditCard: CreditCard): Flow<Resource<Unit, CreditCardError>> {
        return creditCardRepository.addCreditCard(creditCard)
    }
}