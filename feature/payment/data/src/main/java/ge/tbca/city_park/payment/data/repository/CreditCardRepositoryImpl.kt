package ge.tbca.city_park.payment.data.repository

import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.data.mapper.toDTO
import ge.tbca.city_park.payment.data.mapper.toDomain
import ge.tbca.city_park.payment.data.service.CreditCardService
import ge.tbca.city_park.payment.domain.model.CardRequest
import ge.tbca.city_park.payment.domain.model.CreditCard
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreditCardRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val creditCardService: CreditCardService
) : CreditCardRepository {

    override fun addCreditCard(cardRequest: CardRequest): Flow<Resource<CreditCard, ApiError>> {
        return apiHelper.safeCall {
            creditCardService.addCreditCard(cardRequest.toDTO())
        }.mapResource {
            it.toDomain()
        }
    }

    override fun getAllCreditCards(): Flow<Resource<List<CreditCard>, ApiError>> {
        return apiHelper.safeCall {
            creditCardService.getAllCreditCards()
        }.mapResource {
            it.toDomain()
        }
    }

    override fun deleteCreditCardById(id: String): Flow<Resource<Boolean, ApiError>> {
        return apiHelper.safeCall {
            creditCardService.deleteCreditCard(id)
        }
    }
}