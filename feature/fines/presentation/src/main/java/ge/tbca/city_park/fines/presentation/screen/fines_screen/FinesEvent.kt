package ge.tbca.city_park.fines.presentation.screen.fines_screen

sealed interface FinesEvent {
    data object BackButtonClicked: FinesEvent
    data class FineClicked(val fineId: Int) : FinesEvent
    data object Refresh : FinesEvent
}