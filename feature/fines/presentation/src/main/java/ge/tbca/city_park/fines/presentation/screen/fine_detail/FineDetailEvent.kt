package ge.tbca.city_park.fines.presentation.screen.fine_detail

sealed interface FineDetailEvent {
    data object BackButtonClicked : FineDetailEvent
}