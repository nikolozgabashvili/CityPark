package ge.tbca.city_park.auth.data.service

import ge.tbca.city_park.auth.data.model.AddUserRequest
import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @UserId
    @POST(ADD_USER)
    suspend fun setupUser(@Body body: AddUserRequest): BaseResponse<Unit>


    companion object{
        private const val ADD_USER = "users"
    }
}