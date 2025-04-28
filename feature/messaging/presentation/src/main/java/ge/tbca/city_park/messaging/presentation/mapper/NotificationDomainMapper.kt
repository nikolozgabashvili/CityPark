package ge.tbca.city_park.messaging.presentation.mapper

import ge.tbca.city_park.core.ui.util.DateMapper
import ge.tbca.city_park.messaging.domain.model.NotificationDomain
import ge.tbca.city_park.messaging.presentation.model.NotificationUi

fun NotificationDomain.toPresenter() : NotificationUi {
    return NotificationUi(
        id = id,
        userId = userId,
        title = title,
        message = message,
        createdAt = DateMapper.millisToDate(createdAtMillis)
    )
}

fun List<NotificationDomain>.toPresenter() : List<NotificationUi> {
    return map { it.toPresenter() }
}