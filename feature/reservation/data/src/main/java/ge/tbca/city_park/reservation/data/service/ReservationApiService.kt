package ge.tbca.city_park.reservation.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.reservation.data.model.RemoveReservationResponseDTO
import ge.tbca.city_park.reservation.data.model.ReservationDTO
import ge.tbca.city_park.reservation.data.model.ReservationRequestDTO
import ge.tbca.city_park.reservation.domain.model.RemoveReservationRequest
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReservationApiService {
    @UserId
    @GET(RESERVATION_HISTORY)
    suspend fun getReservationsHistory(): BaseResponse<List<ReservationDTO>>

    @UserId
    @POST(CREATE_RESERVATION)
    suspend fun createReservation(@Body body: ReservationRequest): BaseResponse<ReservationRequestDTO>

    @UserId
    @POST(REMOVE_RESERVATION)
    suspend fun removeReservation(@Body body: RemoveReservationRequest): BaseResponse<RemoveReservationResponseDTO>

    companion object {
        private const val RESERVATION_HISTORY = "reservations/history"
        private const val CREATE_RESERVATION = "reservations"
        private const val REMOVE_RESERVATION = "reservations/finish"
    }
}