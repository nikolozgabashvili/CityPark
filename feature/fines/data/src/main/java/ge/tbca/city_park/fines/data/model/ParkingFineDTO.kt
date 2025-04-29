package ge.tbca.city_park.fines.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ParkingFineDTO(
    val id: Int,
    val userId: String,
    val location: String,
    val address: String,
    val carNumber: String,
    val price: Double,
    val description: String,
    val createdAt: Long,
    val isPaid: Boolean
)