package ge.tbca.city_park.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ge.tbca.city_park.app.util.LanguageManager
import ge.tbca.city_park.app.util.LanguageManagerProvider
import javax.inject.Inject

@HiltAndroidApp
class CityParkApp :Application(), LanguageManagerProvider {
    @Inject
    lateinit var languageManager: LanguageManager

    override fun provideLanguageManager(): LanguageManager {
        return languageManager
    }

}