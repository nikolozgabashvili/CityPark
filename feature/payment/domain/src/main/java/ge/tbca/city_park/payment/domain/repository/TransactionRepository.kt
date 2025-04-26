package ge.tbca.city_park.payment.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
     fun buyBalance(cardId: Int, amount: Double, ): Flow<Resource<Unit, ApiError>>
}