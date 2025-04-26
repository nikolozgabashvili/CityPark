package ge.tbca.city_park.payment.data.mapper

import ge.tbca.city_park.payment.data.model.CreditCardDTO
import ge.tbca.city_park.payment.domain.model.CreditCardDomain

fun CreditCardDTO.toDomain(): CreditCardDomain {
    return CreditCardDomain(
        id = id,
        cardNumber = cardNumber,
        holderName = holderName,
        balance = balance,
        expirationMonth = expirationMonth,
        expirationYear = expirationYear,
        cvv = cvv,
        cardType = cardType,
    )
}