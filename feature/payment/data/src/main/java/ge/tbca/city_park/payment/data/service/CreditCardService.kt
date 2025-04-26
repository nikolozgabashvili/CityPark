package ge.tbca.city_park.payment.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.payment.data.model.CardRequestDTO
import ge.tbca.city_park.payment.data.model.CreditCardDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CreditCardService {

    @UserId
    @POST(ADD_CREDIT_CARD)
    suspend fun addCreditCard(@Body cardRequest: CardRequestDTO): BaseResponse<CreditCardDTO>

    @UserId
    @GET(GET_CREDIT_CARDS)
    suspend fun getAllCreditCards(): BaseResponse<List<CreditCardDTO>>

    @UserId
    @DELETE(DELETE_CREDIT_CARD)
    suspend fun deleteCreditCard(@Path("id") id: String): BaseResponse<Boolean>

    companion object {
        private const val ADD_CREDIT_CARD = "users/cards"
        private const val GET_CREDIT_CARDS = "users/cards"
        private const val DELETE_CREDIT_CARD = "users/cards/{id}"
    }
}