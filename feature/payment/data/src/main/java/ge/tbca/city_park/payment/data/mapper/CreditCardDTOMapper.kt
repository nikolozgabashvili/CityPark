package ge.tbca.city_park.payment.data.mapper

import ge.tbca.city_park.payment.data.model.CreditCardDTO
import ge.tbca.city_park.payment.domain.model.CreditCard

fun CreditCardDTO.toDomain(): CreditCard {
    return CreditCard(
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

fun List<CreditCardDTO>.toDomain(): List<CreditCard> {
    return map { it.toDomain() }
}