package ge.tbca.city_park.payment.presentation.mapper

import ge.tbca.city_park.payment.domain.model.CreditCard
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

fun CreditCard.toPresenter(): CreditCardUi {
    return CreditCardUi(
        id = id,
        cardNumber = cardNumber,
        holderName = holderName,
        balance = balance,
        expirationDate = "${expirationMonth.toMonth()}/${expirationYear}",
        cvv = cvv,
        cardType = cardType
    )
}

// add 0 in front of single digit months
private fun Int.toMonth(): String {
    return if (this.toString().length == 1) {
        "0${this}"
    } else {
        this.toString()
    }
}