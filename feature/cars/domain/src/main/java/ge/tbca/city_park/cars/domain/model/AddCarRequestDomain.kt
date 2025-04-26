package ge.tbca.city_park.cars.domain.model

data class AddCarRequestDomain(
    val name: String? = null,
    val carNumber: String,
    val ownerPersonalId: String
)
