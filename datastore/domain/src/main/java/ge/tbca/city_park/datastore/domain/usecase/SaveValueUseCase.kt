package ge.tbca.city_park.datastore.domain.usecase

import androidx.datastore.preferences.core.Preferences
import ge.tbca.city_park.datastore.domain.manager.DataStoreManager
import javax.inject.Inject

class SaveValueUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun <T> invoke(
        key: Preferences.Key<T>,
        value: T
    ) {
        dataStoreManager.saveValue(
            key = key,
            value = value
        )

    }
}