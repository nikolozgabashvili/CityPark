package ge.tbca.city_park.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.GetSavedThemeUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSavedThemeUseCase: GetSavedThemeUseCase
) : ViewModel() {

    val savedTheme = getSavedThemeUseCase()
}