package ge.tbca.city_park.payment.presentation.screen.cards

sealed interface CardsEvent {
    data object AddCardButtonClicked : CardsEvent
    data object NavigateBack : CardsEvent
    data object Refresh : CardsEvent
    data class DeleteCardClicked(val cardId: Int) : CardsEvent
    data object DismissDeleteCardDialog: CardsEvent
    data object DeleteCard: CardsEvent
}