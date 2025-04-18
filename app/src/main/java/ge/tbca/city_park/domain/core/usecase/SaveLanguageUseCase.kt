package ge.tbca.city_park.domain.core.usecase

import ge.tbca.city_park.domain.datastore.DataStoreKeys
import ge.tbca.city_park.domain.model.AppLanguage
import ge.tbca.city_park.domain.repository.DataStoreManager
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(private val dataStoreManager: DataStoreManager) {

    suspend operator fun invoke(language: AppLanguage) {
        dataStoreManager.saveValue(DataStoreKeys.LANGUAGE_KEY, language.name)
    }
}