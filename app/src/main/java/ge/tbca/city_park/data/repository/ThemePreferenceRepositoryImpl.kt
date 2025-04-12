package ge.tbca.city_park.data.repository

import ge.tbca.city_park.domain.datastore.DataStoreKeys.THEME_MODE_KEY
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.DataStoreManager
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemePreferenceRepositoryImpl @Inject constructor(
    private val datastoreManager: DataStoreManager
) : ThemePreferenceRepository {
    override val selectedTheme: Flow<AppThemeOption> =
        datastoreManager.readValue(THEME_MODE_KEY, AppThemeOption.SYSTEM.name)
            .map { storedValue -> AppThemeOption.valueOf(storedValue) }

    override suspend fun saveTheme(option: AppThemeOption) {
        datastoreManager.saveValue(THEME_MODE_KEY, option.name)
    }

    override suspend fun clearTheme() {
        datastoreManager.removeByKey(THEME_MODE_KEY)
    }
}