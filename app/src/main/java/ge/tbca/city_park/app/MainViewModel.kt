package ge.tbca.city_park.app

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.auth.domain.usecase.GetAuthStateUseCase
import ge.tbca.city_park.settings.domain.usecase.GetSavedLanguageUseCase
import ge.tbca.city_park.settings.domain.usecase.GetSavedThemeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSavedThemeUseCase: GetSavedThemeUseCase,
    private val getSavedLanguageUseCase: GetSavedLanguageUseCase,
    private val getAuthStateUseCase: GetAuthStateUseCase
) : BaseViewModel<MainActivityState, MainActivityEffect, Nothing>(MainActivityState()) {

    init {

        observeTheme()
        observeLanguage()
        observeAuthState()

    }


    override fun onEvent(event: Nothing) {}


    private fun observeAuthState() {
        viewModelScope.launch {
            getAuthStateUseCase().collect { isLoggedIn ->
                updateState { copy(isAuthorized = isLoggedIn) }
            }
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            getSavedThemeUseCase().collect {
                updateState { copy(themeOption = it) }
            }
        }
    }


    private fun observeLanguage() {
        viewModelScope.launch {
            getSavedLanguageUseCase().collect {
                sendSideEffect(MainActivityEffect.LanguageChanged(it))
            }
        }
    }

}