package ge.tbca.city_park.payment.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BuyBalanceRequest(
    val cardId: Int,
    val amount: Double,
)
