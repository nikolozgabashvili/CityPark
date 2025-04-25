package ge.tbca.city_park.payment.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.model.CreditCard
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCreditCardsUseCase @Inject constructor(private val creditCardRepository: CreditCardRepository) {

    operator fun invoke(): Flow<Resource<List<CreditCard>, ApiError>> {
        return creditCardRepository.getAllCreditCards()
    }
}