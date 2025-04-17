package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.domain.datastore.DataStoreKeys
import ge.tbca.city_park.domain.repository.DataStoreManager
import ge.tbca.city_park.presentation.core.util.LocaleHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLanguageUseCase @Inject constructor(private val dataStoreManager: DataStoreManager) {

    operator fun invoke() : Flow<String> {
        return dataStoreManager.readValue(DataStoreKeys.LANGUAGE_KEY, LocaleHelper.LANGUAGE_GEORGIAN)
    }
}