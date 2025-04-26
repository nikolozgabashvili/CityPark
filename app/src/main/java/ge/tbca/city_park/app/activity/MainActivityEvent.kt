package ge.tbca.city_park.app.activity

sealed interface MainActivityEvent {
    data object OnSuccessfulAuth : MainActivityEvent
}
