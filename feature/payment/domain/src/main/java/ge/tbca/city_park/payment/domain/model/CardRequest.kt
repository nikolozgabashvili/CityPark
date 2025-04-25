package ge.tbca.city_park.payment.domain.model

data class CardRequest(
    val cardNumber: String,
    val holderName: String,
    val expirationMonth: Int,
    val expirationYear: Int,
    val cvv: String,
    val cardType: String
)