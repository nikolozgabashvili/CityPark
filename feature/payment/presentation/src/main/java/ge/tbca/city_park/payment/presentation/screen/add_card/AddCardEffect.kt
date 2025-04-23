package ge.tbca.city_park.payment.presentation.screen.add_card

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface AddCardEffect {
    data object NavigateBack : AddCardEffect
    data class ShowSnackbar(val message: GenericString) : AddCardEffect
}