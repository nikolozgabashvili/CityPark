package ge.tbca.city_park.payment.domain.repository

import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.error.CreditCardError
import ge.tbca.city_park.payment.domain.model.CreditCard
import kotlinx.coroutines.flow.Flow

interface CreditCardRepository {
    fun addCreditCard(creditCard: CreditCard): Flow<Resource<Unit, CreditCardError>>
    fun getAllCreditCards(): Flow<Resource<List<CreditCard>, CreditCardError>>
    fun getCreditCardById(id: String): Flow<Resource<CreditCard, CreditCardError>>
    fun deleteCreditCardById(id: String): Flow<Resource<Unit, CreditCardError>>
}