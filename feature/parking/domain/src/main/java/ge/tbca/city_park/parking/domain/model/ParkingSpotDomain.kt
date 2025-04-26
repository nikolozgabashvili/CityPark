package ge.tbca.city_park.parking.domain.model

data class ParkingSpotDomain(
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