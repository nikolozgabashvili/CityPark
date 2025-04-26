package ge.tbca.city_park.payment.presentation.screen.add_card

data class AddCardState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val cvv: String = "",
    val cardHolderName: String = "",
    val showCardNumberError: Boolean = false,
    val showExpireDateError: Boolean = false,
    val showCvvError: Boolean = false,
    val showCardHolderNameError: Boolean = false,
    val isLoading: Boolean = false
)