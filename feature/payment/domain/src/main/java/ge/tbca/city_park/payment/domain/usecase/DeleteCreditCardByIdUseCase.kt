package ge.tbca.city_park.payment.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCreditCardByIdUseCase @Inject constructor(private val creditCardRepository: CreditCardRepository) {

    operator fun invoke(id: String): Flow<Resource<Boolean, ApiError>> {
        return creditCardRepository.deleteCreditCardById(id)
    }
}