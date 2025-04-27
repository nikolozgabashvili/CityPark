package ge.tbca.city_park.reservation.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.reservation.data.model.FinishReservationRequestDTO
import ge.tbca.city_park.reservation.data.model.ReservationDTO
import ge.tbca.city_park.reservation.data.model.ReservationRequestDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReservationApiService {
    @UserId
    @GET(RESERVATION_HISTORY)
    suspend fun getReservationsHistory(): BaseResponse<List<ReservationDTO>>

    @UserId
    @POST(CREATE_RESERVATION)
    suspend fun createReservation(@Body body: ReservationRequestDTO): BaseResponse<Unit>

    @UserId
    @POST(FINISH_RESERVATION)
    suspend fun finishReservation(@Body body: FinishReservationRequestDTO): BaseResponse<Unit>

    @UserId
    @GET(RESERVATION_ACTIVE)
    suspend fun getActiveReservation(): BaseResponse<List<ReservationDTO>>

    companion object {
        private const val RESERVATION_HISTORY = "reservations/history"
        private const val CREATE_RESERVATION = "reservations"
        private const val RESERVATION_ACTIVE = "reservations/active"
        private const val FINISH_RESERVATION = "reservations/finish"
    }
}