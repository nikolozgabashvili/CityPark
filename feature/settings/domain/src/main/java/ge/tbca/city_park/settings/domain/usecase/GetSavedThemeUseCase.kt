package ge.tbca.city_park.settings.domain.usecase

import ge.tbca.city_park.datastore.domain.DataStoreKeys
import ge.tbca.city_park.datastore.domain.usecase.GetValueUseCase
import ge.tbca.city_park.settings.domain.model.AppThemeOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSavedThemeUseCase @Inject constructor(
    private val getValueUseCase: GetValueUseCase
) {

    operator fun invoke(): Flow<AppThemeOption> {
        return getValueUseCase(DataStoreKeys.THEME_MODE_KEY, AppThemeOption.SYSTEM.name)
            .map(AppThemeOption::valueOf)
    }
}