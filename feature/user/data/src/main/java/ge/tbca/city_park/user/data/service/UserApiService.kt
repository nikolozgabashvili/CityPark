package ge.tbca.city_park.user.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.user.data.model.UserInfoDTO
import retrofit2.http.GET

interface UserApiService {

    @UserId
    @GET(USERS)
    suspend fun getUserInfo():BaseResponse<UserInfoDTO>


    companion object{
        private const val USERS = "users"
    }
}