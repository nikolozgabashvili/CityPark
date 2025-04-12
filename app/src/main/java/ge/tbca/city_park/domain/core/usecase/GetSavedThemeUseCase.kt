package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedThemeUseCase @Inject constructor(private val themePreferenceRepository: ThemePreferenceRepository) {

    operator fun invoke(): Flow<AppThemeOption> {
        return themePreferenceRepository.selectedTheme
    }
}