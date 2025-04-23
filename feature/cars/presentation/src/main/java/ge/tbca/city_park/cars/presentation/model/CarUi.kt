package ge.tbca.city_park.cars.presentation.model

data class CarUi(
    val id: String,
    val carName: String? = null,
    val plateNumber: String,
    val ownerPersonalNumber: String
)