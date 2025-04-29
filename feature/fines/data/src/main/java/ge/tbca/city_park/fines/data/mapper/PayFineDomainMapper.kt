package ge.tbca.city_park.fines.data.mapper

import ge.tbca.city_park.fines.data.model.PayFineRequestDTO
import ge.tbca.city_park.fines.domain.model.PayFineRequest

fun PayFineRequest.toDTO(): PayFineRequestDTO {
    return PayFineRequestDTO(
        cardId = cardId,
        fineId = fineId
    )
}