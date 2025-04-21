package ge.tbca.city_park.datastore.domain

import androidx.datastore.preferences.core.stringPreferencesKey


object DataStoreKeys {
    val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
    val LANGUAGE_KEY = stringPreferencesKey("language_key")
}