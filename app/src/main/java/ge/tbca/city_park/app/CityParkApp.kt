package ge.tbca.city_park.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import ge.tbca.city_park.R
import ge.tbca.city_park.app.util.LanguageManager
import ge.tbca.city_park.app.util.LanguageManagerProvider
import ge.tbca.city_park.auth.data.worker.SignUpUserWorker.Companion.CHANNEL_ID
import javax.inject.Inject

@HiltAndroidApp
class CityParkApp : Application(), LanguageManagerProvider, Configuration.Provider {

    @Inject
    lateinit var languageManager: LanguageManager

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun provideLanguageManager(): LanguageManager {
        return languageManager
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        setupUploadNotificationChannel()
    }

    private fun setupUploadNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                applicationContext.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            NOTIFICATION_SERVICE
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}