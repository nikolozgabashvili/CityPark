package ge.tbca.city_park.app.activity

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.messaging.domain.usecase.GetAndUpdateMessagingTokenUseCase
import ge.tbca.city_park.settings.domain.usecase.GetSavedLanguageUseCase
import ge.tbca.city_park.settings.domain.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import ge.tbca.city_park.user.domain.usecase.GetUserAuthStateUseCase
import ge.tbca.city_park.user.domain.usecase.IsUserAuthenticatedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSavedThemeUseCase: GetSavedThemeUseCase,
    private val getSavedLanguageUseCase: GetSavedLanguageUseCase,
    private val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
    private val getUserInfoUseCase: FetchUserInfoUseCase,
    private val getAndUpdateTokenUseCase: GetAndUpdateMessagingTokenUseCase,
    private val getUserAuthStateUseCase: GetUserAuthStateUseCase
) : BaseViewModel<MainActivityState, MainActivityEffect, MainActivityEvent>(MainActivityState()) {

    init {

        observeTheme()
        observeLanguage()
        checkIfAuthorized()
        observeAuthState()

    }

    private fun observeAuthState() {
        viewModelScope.launch {
            getUserAuthStateUseCase()
                .collect { isAuthenticated ->
                    if (!isAuthenticated) {
                        updateState { copy(isAuthorized = false) }
                    }
                }
        }
    }


    override fun onEvent(event: MainActivityEvent) {
        when (event) {
            MainActivityEvent.OnSuccessfulAuth -> {
                updateState { copy(isAuthorized = true) }
                updateMessagingToken()
            }
        }
    }


    private fun checkIfAuthorized() {
        viewModelScope.launch {
            val isAuthorized = isUserAuthenticatedUseCase()
            updateState { copy(isAuthorized = isAuthorized) }
            updateMessagingTokenIfNeeded()
        }
    }

    private fun updateMessagingTokenIfNeeded() {
        viewModelScope.launch {
            getUserInfoUseCase().collect {
                if (it is Resource.Success) {
                    if (it.data.fcmToken.isNullOrEmpty()) {
                        updateMessagingToken()
                    }
                }
            }
        }
    }

    private fun updateMessagingToken() {
        viewModelScope.launch(Dispatchers.IO) {
            getAndUpdateTokenUseCase()
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