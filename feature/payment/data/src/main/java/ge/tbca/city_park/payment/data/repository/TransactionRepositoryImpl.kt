package ge.tbca.city_park.payment.data.repository

import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.data.model.BuyBalanceRequest
import ge.tbca.city_park.payment.data.service.TransactionApiService
import ge.tbca.city_park.payment.domain.repository.TransactionRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionApiService: TransactionApiService,
    private val apiHelper: ApiHelper
) : TransactionRepository {

    override fun buyBalance(cardId: Int, amount: Double): Flow<Resource<Unit, ApiError>> {
        return apiHelper.safeCall {
            transactionApiService.buyBalance(BuyBalanceRequest(cardId = cardId, amount = amount))
        }


    }
}