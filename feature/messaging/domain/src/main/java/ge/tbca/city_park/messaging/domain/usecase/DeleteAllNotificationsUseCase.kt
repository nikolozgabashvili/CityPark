package ge.tbca.city_park.messaging.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.messaging.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAllNotificationsUseCase @Inject constructor(private val notificationsRepository: NotificationsRepository) {

    operator fun invoke(): Flow<Resource<Unit, ApiError>> {
        return notificationsRepository.deleteAllNotifications()
    }
}