package ge.tbca.city_park.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.GetCurrentLanguageUseCase
import ge.tbca.city_park.domain.core.usecase.GetSavedThemeUseCase
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSavedThemeUseCase: GetSavedThemeUseCase,
    getCurrentLanguageUseCase: GetCurrentLanguageUseCase
) : ViewModel() {

    val savedTheme = getSavedThemeUseCase()

    val languageFlow = getCurrentLanguageUseCase().produceIn(viewModelScope).receiveAsFlow()

}