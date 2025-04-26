package ge.tbca.city_park.cars.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddCarRequest(
    val name: String? = null,
    val carNumber: String,
    val ownerPersonalId: String
)
