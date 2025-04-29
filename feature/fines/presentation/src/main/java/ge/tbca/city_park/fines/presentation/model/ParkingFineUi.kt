package ge.tbca.city_park.fines.presentation.model

data class ParkingFineUi(
    val id: Int,
    val userId: String,
    val location: String,
    val address: String,
    val carNumber: String,
    val price: Double,
    val description: String,
    val createdAt: String,
    val isPaid: Boolean
)