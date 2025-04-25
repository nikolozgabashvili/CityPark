package ge.tbca.city_park.payment.presentation.screen.cards

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface CardsEffect {
    data object NavigateBack : CardsEffect
    data class ShowSnackbar(val message: GenericString) : CardsEffect
}