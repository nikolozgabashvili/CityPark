package ge.tbca.city_park.payment.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.model.CardRequest
import ge.tbca.city_park.payment.domain.model.CreditCard
import kotlinx.coroutines.flow.Flow

interface CreditCardRepository {
    fun addCreditCard(cardRequest: CardRequest): Flow<Resource<CreditCard, ApiError>>
    fun getAllCreditCards(): Flow<Resource<List<CreditCard>, ApiError>>
    fun deleteCreditCardById(id: String): Flow<Resource<Boolean, ApiError>>
}