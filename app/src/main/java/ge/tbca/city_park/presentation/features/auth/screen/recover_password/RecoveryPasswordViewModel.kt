package ge.tbca.city_park.presentation.features.auth.screen.recover_password

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.ValidateEmailUseCase
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.core.util.isLoading
import ge.tbca.city_park.domain.features.auth.usecase.RecoverPasswordUseCase
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import ge.tbca.city_park.presentation.core.extension.toGenericString
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val recoverPasswordUseCase: RecoverPasswordUseCase,
) : BaseViewModel<RecoveryPasswordState, RecoverPasswordEffect, RecoveryPasswordEvent>(
    RecoveryPasswordState()
) {

    override fun onEvent(event: RecoveryPasswordEvent) {
        when (event) {
            is RecoveryPasswordEvent.BackButtonClicked -> navigateBack()
            is RecoveryPasswordEvent.EmailChanged -> updateEmail(event.email)
            is RecoveryPasswordEvent.SubmitButtonClicked -> submitEmail()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(RecoverPasswordEffect.NavigateBack)
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
            viewModelScope.launch {
                recoverPasswordUseCase(state.email).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }

                    when (resource) {
                        is Resource.Success -> {
                            sendSideEffect(RecoverPasswordEffect.Success)
                        }

                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(RecoverPasswordEffect.Error(error))
                        }

                        Resource.Loading -> Unit
                    }
                }
            }
        } else {
            updateState { copy(showEmailError = true) }
        }
    }
}