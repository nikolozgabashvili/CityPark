package ge.tbca.city_park.fines.presentation.screen.fines_screen

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface FinesEffect {
    data object NavigateBack : FinesEffect
    data class Error(val error: GenericString) : FinesEffect
    data class NavigateToFineDetail(val fineId: Int) : FinesEffect
}