package ge.tbca.city_park.datastore.domain.usecase

import androidx.datastore.preferences.core.Preferences
import ge.tbca.city_park.datastore.domain.manager.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class GetValueUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    operator fun <T> invoke(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return dataStoreManager.readValue(
            key = key,
            defaultValue = defaultValue
        ).distinctUntilChanged()
    }
}