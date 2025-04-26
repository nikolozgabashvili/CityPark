package ge.tbca.city_park.cars.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CarDTO(
    val id: Int,
    val name: String?,
    val carNumber: String,
    val ownerPersonalId: String,
)
