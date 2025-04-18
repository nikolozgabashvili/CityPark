package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.domain.datastore.DataStoreKeys
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.DataStoreManager
import javax.inject.Inject

class SaveThemeUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    suspend operator fun invoke(theme: AppThemeOption) {
        dataStoreManager.saveValue(DataStoreKeys.THEME_MODE_KEY,theme.name)
    }
}