package ge.tbca.city_park.cars.domain.model

data class CarDomain(
    val id: Int,
    val carName: String? = null,
    val plateNumber: String,
    val ownerPersonalNumber: String
)