package ge.tbca.city_park.settings.domain.usecase

import ge.tbca.city_park.datastore.domain.DataStoreKeys
import ge.tbca.city_park.datastore.domain.usecase.GetValueUseCase
import ge.tbca.city_park.settings.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSavedLanguageUseCase @Inject constructor(
    private val getValueUseCase:GetValueUseCase
) {

    operator fun invoke(): Flow<AppLanguage> {
        return getValueUseCase(DataStoreKeys.LANGUAGE_KEY, AppLanguage.GEORGIAN.name)
            .map(AppLanguage::valueOf)
    }
}