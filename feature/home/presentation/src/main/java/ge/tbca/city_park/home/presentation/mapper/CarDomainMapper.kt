package ge.tbca.city_park.home.presentation.mapper

import ge.tbca.city_park.core.ui.util.DateMapper
import ge.tbca.city_park.home.presentation.model.ReservationUi
import ge.tbca.city_park.reservation.domain.model.ReservationDomain

fun ReservationDomain.toPresenter(): ReservationUi {
    return ReservationUi(
        id = id,
        carNumber = carNumber,
        zoneCode = zoneCode,
        startTime = DateMapper.millisToDate(createdAt),
    )
}