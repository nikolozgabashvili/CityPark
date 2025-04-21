package ge.tbca.city_park.settings.domain.usecase

import ge.tbca.city_park.datastore.domain.DataStoreKeys
import ge.tbca.city_park.datastore.domain.usecase.SaveValueUseCase
import ge.tbca.city_park.settings.domain.model.AppLanguage
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(
    private val saveValueUseCase: SaveValueUseCase
) {

    suspend operator fun invoke(language: AppLanguage) {
        saveValueUseCase(DataStoreKeys.LANGUAGE_KEY, language.name)
    }
}