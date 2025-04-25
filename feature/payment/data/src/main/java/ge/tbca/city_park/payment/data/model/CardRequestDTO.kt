package ge.tbca.city_park.payment.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CardRequestDTO(
    val cardNumber: String,
    val holderName: String,
    val expirationMonth: Int,
    val expirationYear: Int,
    val cvv: String,
    val cardType: String
)