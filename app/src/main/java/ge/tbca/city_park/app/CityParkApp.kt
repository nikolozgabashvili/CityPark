package ge.tbca.city_park.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import ge.tbca.city_park.di.dataStore
import ge.tbca.city_park.domain.datastore.DataStoreKeys
import ge.tbca.city_park.presentation.core.util.LocaleHelper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

@HiltAndroidApp
class CityParkApp :Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: CityParkApp
            private set

        fun getLanguage(context: Context): String {
             return runBlocking {
                context.dataStore.data.map { preferences ->
                    preferences[DataStoreKeys.LANGUAGE_KEY] ?: LocaleHelper.DEFAULT_LANGUAGE
                }.first()
            }
        }
    }
}