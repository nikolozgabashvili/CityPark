package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.domain.datastore.DataStoreKeys
import ge.tbca.city_park.domain.repository.DataStoreManager
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(private val dataStoreManager: DataStoreManager) {

    suspend operator fun invoke(languageCode: String) {
        dataStoreManager.saveValue(DataStoreKeys.LANGUAGE_KEY, languageCode)
    }
}