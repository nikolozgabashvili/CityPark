package ge.tbca.city_park.payment.presentation.screen.cards

import ge.tbca.city_park.payment.presentation.model.CreditCardUi

data class CardsState(
    val cardsList: List<CreditCardUi> = emptyList(),
    val isLoading: Boolean = false
)