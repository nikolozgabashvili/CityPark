package ge.tbca.city_park.fines.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.fines.data.model.ParkingFineDTO
import ge.tbca.city_park.fines.data.model.PayFineRequestDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FineApiService {

    @UserId
    @GET(FINES)
    suspend fun getFines(): BaseResponse<List<ParkingFineDTO>>

    @UserId
    @POST(PAY_FINES)
    suspend fun payFines(@Body body: PayFineRequestDTO): BaseResponse<Unit>

    @UserId
    @GET(GET_FINES_ID)
    suspend fun getFinesById(@Path("id") id: Int): BaseResponse<ParkingFineDTO>


    companion object {

        private const val FINES = "fines"
        private const val PAY_FINES = "fines/pay"
        private const val GET_FINES_ID = "fines/{id}"


    }
}