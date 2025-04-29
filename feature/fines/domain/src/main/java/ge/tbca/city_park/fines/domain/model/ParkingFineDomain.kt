package ge.tbca.city_park.fines.domain.model

data class ParkingFineDomain(
    val id: Int,
    val userId: String,
    val address: String,
    val carNumber: String,
    val price: Double,
    val description: String,
    val createdAt: Long,
    val isPaid: Boolean
)
