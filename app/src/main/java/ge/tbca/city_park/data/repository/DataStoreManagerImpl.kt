package ge.tbca.city_park.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import ge.tbca.city_park.domain.repository.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreManager {
    override suspend fun <T> saveValue(key: Preferences.Key<T>, value: T) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preference ->
                preference[key] = value
            }
        }
    }

    override fun <T> readValue(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.map {
            it[key] ?: defaultValue
        }
    }

    override suspend fun <T> removeByKey(key: Preferences.Key<T>) {
        withContext(Dispatchers.IO) {
            dataStore.edit {
                it.remove(key)
            }
        }
    }
}