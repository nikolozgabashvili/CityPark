package ge.tbca.city_park.payment.presentation.model

import ge.tbca.city_park.payment.domain.model.CardType

data class CreditCardUi(
    val id: Int,
    val cardNumber: String,
    val holderName: String,
    val balance: Double,
    val expirationDate: String,
    val cvv: String,
    val cardType: CardType
)