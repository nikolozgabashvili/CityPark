package ge.tbca.city_park.fines.presentation.screen.fine_detail

sealed interface FineDetailEvent {
    data object BackButtonClicked : FineDetailEvent
    data object OnPaymentClicked : FineDetailEvent
    data object OnRetry : FineDetailEvent
    data object OnCardsRetry : FineDetailEvent
    data object SelectCardClicked : FineDetailEvent
    data object CloseCardsDropdown : FineDetailEvent
    data object NavigateToAddCard : FineDetailEvent
    data class OnCardSelected(val cardId: Int) : FineDetailEvent
}