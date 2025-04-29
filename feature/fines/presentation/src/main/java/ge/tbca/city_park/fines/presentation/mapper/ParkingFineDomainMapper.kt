package ge.tbca.city_park.fines.presentation.mapper

import ge.tbca.city_park.core.ui.util.DateMapper
import ge.tbca.city_park.fines.domain.model.ParkingFineDomain
import ge.tbca.city_park.fines.presentation.model.ParkingFineUi

fun ParkingFineDomain.toPresenter(): ParkingFineUi {
    return ParkingFineUi(
        id = id,
        userId = userId,
        address = address,
        carNumber = carNumber,
        price = price,
        description = description,
        createdAt = DateMapper.millisToDate(createdAt),
        isPaid = isPaid
    )
}