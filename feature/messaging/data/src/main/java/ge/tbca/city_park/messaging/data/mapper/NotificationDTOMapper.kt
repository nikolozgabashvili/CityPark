package ge.tbca.city_park.messaging.data.mapper

import ge.tbca.city_park.messaging.data.model.NotificationDTO
import ge.tbca.city_park.messaging.domain.model.NotificationDomain

fun NotificationDTO.toDomain(): NotificationDomain {
    return NotificationDomain(
        id = id,
        userId = userId,
        title = title,
        message = message,
        createdAtMillis = createdAtMillis
    )
}

fun List<NotificationDTO>.toDomain(): List<NotificationDomain> {
    return map { it.toDomain() }
}