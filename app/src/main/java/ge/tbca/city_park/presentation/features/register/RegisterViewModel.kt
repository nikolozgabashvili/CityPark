package ge.tbca.city_park.presentation.features.register

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() :
    BaseViewModel<RegisterState, RegisterEffect, RegisterEvent>(RegisterState()) {

    override fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> {}
            is RegisterEvent.PasswordChanged -> updatePassword(event.password)
            is RegisterEvent.PasswordVisibilityChanged -> {}
            is RegisterEvent.RegisterButtonClicked -> {}
            is RegisterEvent.GoogleLoginButtonClicked -> {}
            is RegisterEvent.BackButtonClicked -> {}
            is RegisterEvent.RepeatPasswordChanged -> {}
            is RegisterEvent.RepeatPasswordVisibilityChanged -> {}
        }
    }

    private fun updatePassword(password: String) {
        updateState { copy(password = password) }
    }
}