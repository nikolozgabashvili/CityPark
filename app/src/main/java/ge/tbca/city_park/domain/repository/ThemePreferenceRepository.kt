package ge.tbca.city_park.domain.repository

import ge.tbca.city_park.domain.model.AppThemeOption
import kotlinx.coroutines.flow.Flow

interface ThemePreferenceRepository {
    val selectedTheme: Flow<AppThemeOption>
    suspend fun saveTheme(option: AppThemeOption)
    suspend fun clearTheme()
}