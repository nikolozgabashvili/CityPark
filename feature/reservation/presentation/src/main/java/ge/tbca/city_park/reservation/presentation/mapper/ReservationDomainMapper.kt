package ge.tbca.city_park.reservation.presentation.mapper

import ge.tbca.city_park.reservation.presentation.model.ReservationUi
import ge.tbca.city_park.core.ui.util.DateMapper
import ge.tbca.city_park.reservation.domain.model.ReservationDomain

fun ReservationDomain.toPresenter() : ReservationUi {
    return ReservationUi(
        id = id,
        parkingSpotId = parkingSpotId,
        zoneCode = zoneCode,
        carNumber = carNumber,
        createdAt = DateMapper.millisToDate(createdAt),
        active = active,
        endedAt = endedAt?.let {
            DateMapper.millisToDate(it)
        },
        cost = cost
    )
}

fun List<ReservationDomain>.toPresenter(): List<ReservationUi> {
    return map { it.toPresenter() }
}