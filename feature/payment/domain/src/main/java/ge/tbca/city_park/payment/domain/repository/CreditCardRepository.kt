package ge.tbca.city_park.payment.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.model.CardRequestDomain
import ge.tbca.city_park.payment.domain.model.CreditCardDomain
import kotlinx.coroutines.flow.Flow

interface CreditCardRepository {
    fun addCreditCard(cardRequestDomain: CardRequestDomain): Flow<Resource<CreditCardDomain, ApiError>>
    fun getAllCreditCards(): Flow<Resource<List<CreditCardDomain>, ApiError>>
    fun deleteCreditCardById(id: Int): Flow<Resource<Unit, ApiError>>
}