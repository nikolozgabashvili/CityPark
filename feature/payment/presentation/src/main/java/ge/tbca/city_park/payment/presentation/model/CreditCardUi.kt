package ge.tbca.city_park.payment.presentation.model

import java.util.UUID

data class CreditCardUi(
    val id: UUID,
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    val cardHolderName: String
)