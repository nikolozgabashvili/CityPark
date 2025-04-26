package ge.tbca.city_park.payment.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuyBalanceUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(
        cardId: Int,
        amount: Double,
    ): Flow<Resource<Unit, ApiError>> {
        return transactionRepository.buyBalance(cardId, amount)
    }
}