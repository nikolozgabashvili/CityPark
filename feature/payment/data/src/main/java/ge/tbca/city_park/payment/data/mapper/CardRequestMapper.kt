package ge.tbca.city_park.payment.data.mapper

import ge.tbca.city_park.payment.data.model.CardRequestDTO
import ge.tbca.city_park.payment.domain.model.CardRequest

fun CardRequest.toDTO(): CardRequestDTO {
    return CardRequestDTO(
        cardNumber = cardNumber,
        holderName = holderName,
        expirationMonth = expirationMonth,
        expirationYear = expirationYear,
        cvv = cvv,
        cardType = cardType
    )
}