package ge.tbca.city_park.payment.data.model

import ge.tbca.city_park.payment.domain.model.CardType
import kotlinx.serialization.Serializable

@Serializable
data class CreditCardDTO(
    val id: Int,
    val cardNumber: String,
    val holderName: String,
    val balance: Double,
    val expirationMonth: Int,
    val expirationYear: Int,
    val cvv: String,
    val cardType: CardType
)