package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import javax.inject.Inject

class SaveThemeUseCase @Inject constructor(private val themePreferenceRepository: ThemePreferenceRepository) {

    suspend operator fun invoke(theme: AppThemeOption) {
        themePreferenceRepository.saveTheme(theme)
    }
}