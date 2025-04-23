package ge.tbca.city_park.cars.domain.model

data class Car(
    val id: String,
    val carName: String? = null,
    val plateNumber: String,
    val ownerPersonalNumber: String
)