package ge.tbca.city_park.messaging.data.repository

import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.extension.toEmptyResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.messaging.data.mapper.toDomain
import ge.tbca.city_park.messaging.data.service.NotificationApiService
import ge.tbca.city_park.messaging.domain.model.NotificationDomain
import ge.tbca.city_park.messaging.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val notificationApiService: NotificationApiService
) : NotificationsRepository {

    override fun getAllNotifications(): Flow<Resource<List<NotificationDomain>, ApiError>> {
        return apiHelper.safeCall {
            notificationApiService.getAllNotifications()
        }.mapResource {
            it.toDomain()
        }
    }

    override fun deleteAllNotifications(): Flow<Resource<Unit, ApiError>> {
        return apiHelper.safeCall {
            notificationApiService.deleteAllNotifications()
        }.toEmptyResource()
    }

    override fun deleteNotificationById(id: Int): Flow<Resource<Unit, ApiError>> {
        return apiHelper.safeCall {
            notificationApiService.deleteNotificationById(id)
        }.toEmptyResource()
    }
}