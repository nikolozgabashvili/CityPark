package ge.tbca.city_park.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.domain.datastore.DataStoreKeys
import ge.tbca.city_park.domain.repository.DataStoreManager
import ge.tbca.city_park.presentation.core.util.LocaleHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSavedThemeUseCase: GetSavedThemeUseCase,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val savedTheme = getSavedThemeUseCase()

    fun observeLanguage(): Flow<String> {
        return dataStoreManager.readValue(DataStoreKeys.LANGUAGE_KEY, LocaleHelper.DEFAULT_LANGUAGE)
    }
}