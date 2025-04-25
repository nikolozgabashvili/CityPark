package ge.tbca.city_park.messaging.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import ge.tbca.city_park.messaging.domain.usecase.UpdateMessagingTokenUseCase
import ge.tbca.city_park.messaging.presentation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PushNotificationService :FirebaseMessagingService() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var updateMessagingTokenUseCase: UpdateMessagingTokenUseCase

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
            showNotification(it)
        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        scope.launch {
            updateMessagingTokenUseCase(token)
        }

    }

    private fun showNotification(notification: RemoteMessage.Notification) {
        val channelId = getString(R.string.default_notification_channel_id)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.default_notification_channel_id),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }


        val intent = Intent().setClassName(
            this,
            "ge.tbca.city_park.app.activity.MainActivity"
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(notification.title)
            .setContentText(notification.body)
            .setSmallIcon(R.drawable.ic_car)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}