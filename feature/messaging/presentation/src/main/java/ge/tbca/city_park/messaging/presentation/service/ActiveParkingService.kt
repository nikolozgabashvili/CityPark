package ge.tbca.city_park.messaging.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.getSystemService
import ge.tbca.city_park.messaging.presentation.R
import ge.tbca.city_park.messaging.presentation.util.Timer
import ge.tbca.city_park.messaging.presentation.util.formatted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.runningReduce


class ActiveParkingService : Service() {

    private val notificationManager by lazy {
        getSystemService<NotificationManager>()!!
    }

    private val baseNotification by lazy {
        NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_car)
            .setContentTitle(applicationContext.getString(R.string.active_parking))
    }

    private var serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            ACTION_START -> {
                start()
            }

            ACTION_STOP -> stop()
        }
        return START_STICKY
    }

    private fun stop() {
        stopSelf()
        isServiceActive = false
        serviceScope.cancel()

        serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    }


    private fun start() {
        if (!isServiceActive) {
            isServiceActive = true
            createNotificationChannel()
            val intent = Intent().setClassName(
                this,
                "ge.tbca.city_park.app.activity.MainActivity"
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }

            val pendingIntent = TaskStackBuilder.create(applicationContext).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }

            val notification =
                baseNotification
                    .setContentText("00:00:00")
                    .setContentIntent(pendingIntent)
                    .build()

            startForeground(NOTIFICATION_ID, notification)
            updateNotification()
        }

    }

    private fun updateNotification() {
        Timer.timeAndEmit().runningReduce { total, new ->
            total + new
        }.onEach {
            val notification = baseNotification
                .setContentText(it.formatted())
                .setOngoing(true)
                .build()
            notificationManager.notify(NOTIFICATION_ID, notification)
        }.launchIn(serviceScope)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                application.getString(R.string.foreground_service_channel_name),
                NotificationManager.IMPORTANCE_LOW

            ).apply { lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC }
            notificationManager.createNotificationChannel(channel)
        }
    }


    companion object {
        const val NOTIFICATION_ID = 2
        const val CHANNEL_ID = "ActiveParkingForegroundService"

        var isServiceActive = false
        private const val ACTION_START = "ACTION_START"
        private const val ACTION_STOP = "ACTION_STOP"

        fun createStartIntent(context: Context): Intent {
            return Intent(context, ActiveParkingService::class.java).apply {
                action = ACTION_START
            }
        }

        fun createStopIntent(context: Context): Intent {
            return Intent(context, ActiveParkingService::class.java).apply {
                action = ACTION_STOP
            }
        }
    }

}