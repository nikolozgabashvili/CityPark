package ge.tbca.city_park.settings.domain.usecase

import ge.tbca.city_park.datastore.domain.DataStoreKeys
import ge.tbca.city_park.datastore.domain.usecase.SaveValueUseCase
import ge.tbca.city_park.settings.domain.model.AppThemeOption
import javax.inject.Inject

class SaveThemeUseCase @Inject constructor(
    private val saveValueUseCase: SaveValueUseCase
) {

    suspend operator fun invoke(theme: AppThemeOption) {
        saveValueUseCase(DataStoreKeys.THEME_MODE_KEY,theme.name)
    }
}