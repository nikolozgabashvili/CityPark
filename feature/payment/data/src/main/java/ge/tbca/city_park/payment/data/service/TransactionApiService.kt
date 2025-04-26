package ge.tbca.city_park.payment.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.payment.data.model.BuyBalanceRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApiService {

    @UserId
    @POST(BUY_BALANCE)
    suspend fun buyBalance(@Body body: BuyBalanceRequest): BaseResponse<Unit>


    companion object {
        private const val BUY_BALANCE = "users/buy-balance"
    }
}