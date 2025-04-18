package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.domain.datastore.DataStoreKeys
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSavedThemeUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    operator fun invoke(): Flow<AppThemeOption> {
        return dataStoreManager.readValue(DataStoreKeys.THEME_MODE_KEY, AppThemeOption.SYSTEM.name)
            .map(AppThemeOption::valueOf)
    }
}