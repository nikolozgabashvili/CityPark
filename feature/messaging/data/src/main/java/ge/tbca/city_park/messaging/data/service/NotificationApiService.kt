package ge.tbca.city_park.messaging.data.service

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import ge.tbca.city_park.messaging.data.model.NotificationDTO
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface NotificationApiService {

    @UserId
    @GET(NOTIFICATIONS)
    suspend fun getAllNotifications(): BaseResponse<List<NotificationDTO>>

    @UserId
    @DELETE(DELETE_ALL_NOTIFICATIONS)
    suspend fun deleteAllNotifications(): BaseResponse<Boolean>

    @UserId
    @DELETE(DELETE_NOTIFICATION_BY_ID)
    suspend fun deleteNotificationById(@Path("id") id: Int): BaseResponse<Boolean>

    companion object {
        private const val NOTIFICATIONS = "notifications"
        private const val DELETE_ALL_NOTIFICATIONS = "notifications"
        private const val DELETE_NOTIFICATION_BY_ID = "notifications/{id}"
    }
}