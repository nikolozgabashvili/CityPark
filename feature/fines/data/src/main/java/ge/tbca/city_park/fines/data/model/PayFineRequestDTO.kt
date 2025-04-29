package ge.tbca.city_park.fines.data.model

import kotlinx.serialization.Serializable


@Serializable
data class PayFineRequestDTO(
    val cardId: Int,
    val fineId: Int
)