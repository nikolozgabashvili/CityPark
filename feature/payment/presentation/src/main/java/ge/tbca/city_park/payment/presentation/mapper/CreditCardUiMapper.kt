package ge.tbca.city_park.payment.presentation.mapper

import ge.tbca.city_park.payment.domain.model.CreditCard
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

fun CreditCardUi.toDomain(): CreditCard {
    return CreditCard(
        id = id,
        cardNumber = cardNumber,
        expireDate = expireDate,
        cvv = cvv,
        cardHolderName = cardHolderName
    )
}