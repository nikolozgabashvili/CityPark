package ge.tbca.city_park.data.repository

import ge.tbca.city_park.data.datastore.DataStoreKeys.THEME_MODE_KEY
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.DataStoreRepository
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemePreferenceRepositoryImpl @Inject constructor(
    private val datastoreRepository: DataStoreRepository
) : ThemePreferenceRepository {
    override val selectedTheme: Flow<AppThemeOption> =
        datastoreRepository.readValue(THEME_MODE_KEY, AppThemeOption.SYSTEM.name)
            .map { storedValue ->
                runCatching { AppThemeOption.valueOf(storedValue) }
                    .getOrDefault(AppThemeOption.SYSTEM)
            }

    override suspend fun saveTheme(option: AppThemeOption) {
        datastoreRepository.saveValue(THEME_MODE_KEY, option.name)
    }

    override suspend fun clearTheme() {
        datastoreRepository.removeByKey(THEME_MODE_KEY)
    }
}