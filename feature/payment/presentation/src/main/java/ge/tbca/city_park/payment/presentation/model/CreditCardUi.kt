package ge.tbca.city_park.payment.presentation.model

data class CreditCardUi(
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    val cardHolderName: String
)