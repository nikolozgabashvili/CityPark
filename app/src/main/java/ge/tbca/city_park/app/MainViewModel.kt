package ge.tbca.city_park.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.settings.domain.usecase.GetSavedLanguageUseCase
import ge.tbca.city_park.settings.domain.usecase.GetSavedThemeUseCase
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSavedThemeUseCase: GetSavedThemeUseCase,
    getSavedLanguageUseCase: GetSavedLanguageUseCase
) : ViewModel() {

    val savedTheme = getSavedThemeUseCase()

    val languageFlow = getSavedLanguageUseCase().produceIn(viewModelScope).receiveAsFlow()

}