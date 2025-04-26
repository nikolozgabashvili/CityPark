package ge.tbca.city_park.messaging.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
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
class PushNotificationService : FirebaseMessagingService() {

    @Inject
    lateinit var updateMessagingTokenUseCase: UpdateMessagingTokenUseCase

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val notificationManager by lazy {
        getSystemService<NotificationManager>()!!
    }

    private val baseNotification by lazy {
        NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.default_notification_channel_id)
        ).setSmallIcon(R.drawable.ic_car)
            .setAutoCancel(true)
    }


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
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    private fun showNotification(notificationData: RemoteMessage.Notification) {

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



        val notification = baseNotification
            .setContentTitle(notificationData.title)
            .setContentText(notificationData.body)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(1, notification)

    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                /* id = */ applicationContext.getString(R.string.default_notification_channel_id),
                /* name = */ applicationContext.getString(R.string.push_notification_channel_name),
                /* importance = */ NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}