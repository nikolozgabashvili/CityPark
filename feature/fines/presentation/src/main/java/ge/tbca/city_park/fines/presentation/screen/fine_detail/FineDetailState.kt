package ge.tbca.city_park.fines.presentation.screen.fine_detail

import ge.tbca.city_park.core.ui.util.GenericString
import ge.tbca.city_park.fines.presentation.model.ParkingFineUi
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

data class FineDetailState(
    val fine: ParkingFineUi? = null,
    val isLoading: Boolean = false,
    val error: GenericString? = null,
    val paymentInProgress: Boolean = false,
    val cards: List<CreditCardUi> = emptyList(),
    val selectedCardId: Int? = null,
    val cardsLoading: Boolean = false,
    val showCardsBottomSheet: Boolean = false,
) {


    val selectedCard: CreditCardUi?
        get() = cards.find { it.id == selectedCardId }

    val paymentButtonEnabled: Boolean
        get() = fine != null && !isLoading && !paymentInProgress && selectedCard != null
}