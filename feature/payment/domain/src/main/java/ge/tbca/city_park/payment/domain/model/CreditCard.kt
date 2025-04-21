package ge.tbca.city_park.payment.domain.model

data class CreditCard(
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    val cardHolderName: String
)