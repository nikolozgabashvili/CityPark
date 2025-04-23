package ge.tbca.city_park.payment.domain.model

import java.util.UUID

data class CreditCard(
    val id: UUID,
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    val cardHolderName: String
)