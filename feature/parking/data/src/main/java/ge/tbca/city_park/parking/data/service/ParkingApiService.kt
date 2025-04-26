package ge.tbca.city_park.parking.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.parking.data.model.ParkingSpotDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ParkingApiService {

    @GET(PARKING_SPOTS)
    suspend fun getParkingSpots(): BaseResponse<List<ParkingSpotDTO>>

    @GET(PARKING_SPOT_BY_ZONE_CODE)
    suspend fun getParkingSpotByZoneCode(@Path("zoneCode") zoneCode: String): BaseResponse<ParkingSpotDTO>

    companion object {
        private const val PARKING_SPOTS = "parking-spots"
        private const val PARKING_SPOT_BY_ZONE_CODE = "parking-spots/{zoneCode}"
    }
}