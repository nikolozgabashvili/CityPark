package ge.tbca.city_park.parking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ParkingSpotDTO(
    val id: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val city: String,
    val district: String,
    val parkingSpotAmount: Int,
    val availableSpots: Int,
    val zoneCode: String
)