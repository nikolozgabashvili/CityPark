package ge.tbca.city_park.payment.presentation.screen.cards

sealed interface CardsEvent {
    data object NavigateBack: CardsEvent
    data object Refresh : CardsEvent
}