package ge.tbca.city_park.fines.presentation.screen.fines_screen

import ge.tbca.city_park.core.ui.util.GenericString
import ge.tbca.city_park.fines.presentation.model.ParkingFineUi

data class FinesState(
    val fines: List<ParkingFineUi> = emptyList(),
    val error: GenericString? = null,
    val isLoading: Boolean = false
)