package ge.tbca.city_park.payment.data.repository

import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.data.mapper.toDTO
import ge.tbca.city_park.payment.data.mapper.toDomain
import ge.tbca.city_park.payment.data.service.CreditCardService
import ge.tbca.city_park.payment.domain.model.CardRequestDomain
import ge.tbca.city_park.payment.domain.model.CreditCardDomain
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreditCardRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val creditCardService: CreditCardService
) : CreditCardRepository {

    override fun addCreditCard(cardRequestDomain: CardRequestDomain): Flow<Resource<CreditCardDomain, ApiError>> {
        return apiHelper.safeCall {
            creditCardService.addCreditCard(cardRequestDomain.toDTO())
        }.mapResource { creditCardDTO ->
            creditCardDTO.toDomain()
        }
    }

    override fun getAllCreditCards(): Flow<Resource<List<CreditCardDomain>, ApiError>> {
        return apiHelper.safeCall {
            creditCardService.getAllCreditCards()
        }.mapResource { list ->
            list.map { creditCardDTO ->
                creditCardDTO.toDomain()
            }
        }
    }

    override fun deleteCreditCardById(id: String): Flow<Resource<Boolean, ApiError>> {
        return apiHelper.safeCall {
            creditCardService.deleteCreditCard(id)
        }
    }
}