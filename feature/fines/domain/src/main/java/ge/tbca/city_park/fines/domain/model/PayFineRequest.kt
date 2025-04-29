package ge.tbca.city_park.fines.domain.model

data class PayFineRequest(
    val fineId: Int,
    val cardId: Int
)