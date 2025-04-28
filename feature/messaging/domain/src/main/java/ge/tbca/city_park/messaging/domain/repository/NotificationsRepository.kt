package ge.tbca.city_park.messaging.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.messaging.domain.model.NotificationDomain
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {
    fun getAllNotifications(): Flow<Resource<List<NotificationDomain>, ApiError>>
    fun deleteAllNotifications(): Flow<Resource<Unit, ApiError>>
    fun deleteNotificationById(id: Int): Flow<Resource<Unit, ApiError>>
}