package ge.tbca.city_park.presentation.features.forgot_password

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.ValidateEmailUseCase
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase
) : BaseViewModel<ForgotPasswordState, ForgotPasswordEffect, ForgotPasswordEvent>(
    ForgotPasswordState()
) {

    override fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.BackButtonClicked -> navigateBack()
            is ForgotPasswordEvent.EmailChanged -> updateEmail(event.email)
            is ForgotPasswordEvent.SubmitButtonClicked -> submitEmail()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(ForgotPasswordEffect.NavigateBack)
        }
    }

    private fun updateEmail(email: String) {
        updateState {
            copy(
                email = email,
                showEmailError = false
            )
        }
    }

    private fun submitEmail() {
        val isEmailValid = validateEmailUseCase(state.email)

        if (isEmailValid) {
            // TODO submit email
        } else {
            updateState { copy(showEmailError = true) }
        }
    }
}