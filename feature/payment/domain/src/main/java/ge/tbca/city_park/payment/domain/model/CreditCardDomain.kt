package ge.tbca.city_park.payment.domain.model

data class CreditCardDomain(
    val id: Int,
    val cardNumber: String,
    val holderName: String,
    val balance: Double,
    val expirationMonth: Int,
    val expirationYear: Int,
    val cvv: String,
    val cardType: CardType
)