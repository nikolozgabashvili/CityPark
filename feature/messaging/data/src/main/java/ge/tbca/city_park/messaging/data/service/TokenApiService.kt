package ge.tbca.city_park.messaging.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.messaging.data.model.UpdateTokenRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface TokenApiService {

    @UserId
    @POST(UPDATE_TOKEN)
    suspend fun updateMessagingToken(@Body body: UpdateTokenRequest): BaseResponse<Unit>

    @UserId
    @DELETE(DELETE_TOKEN)
    suspend fun deleteMessagingToken(): BaseResponse<Unit>

    companion object{
        private const val UPDATE_TOKEN = "users/update-fcm-token"
        private const val DELETE_TOKEN = "users/fcm-token"
    }
}