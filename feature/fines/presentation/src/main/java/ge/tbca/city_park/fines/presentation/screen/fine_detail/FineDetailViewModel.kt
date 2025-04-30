package ge.tbca.city_park.fines.presentation.screen.fine_detail

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.fines.domain.model.PayFineRequest
import ge.tbca.city_park.fines.domain.usecase.GetFineByIdUseCase
import ge.tbca.city_park.fines.domain.usecase.PayFineUseCase
import ge.tbca.city_park.fines.presentation.mapper.toPresenter
import ge.tbca.city_park.payment.domain.usecase.GetAllCreditCardsUseCase
import ge.tbca.city_park.payment.presentation.mapper.toPresenter
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = FineDetailViewModel.Factory::class)
class FineDetailViewModel @AssistedInject constructor(
    private val getFineDetailsUseCase: GetFineByIdUseCase,
    private val getCardsUseCase: GetAllCreditCardsUseCase,
    private val payFineUseCase: PayFineUseCase,
    @Assisted("fineId") private val fineId: Int,
) : BaseViewModel<FineDetailState, FineDetailEffect, FineDetailEvent>(FineDetailState()) {

    init {
        getFineDetails()
    }


    override fun onEvent(event: FineDetailEvent) {

        when (event) {
            is FineDetailEvent.BackButtonClicked -> navigateBack()

            is FineDetailEvent.OnPaymentClicked -> startPayment()

            is FineDetailEvent.OnRetry -> getFineDetails()

            is FineDetailEvent.OnCardsRetry -> getCards()

            is FineDetailEvent.SelectCardClicked -> openCardsBottomSheet()
            is FineDetailEvent.CloseCardsDropdown -> closeDropdown()
            is FineDetailEvent.NavigateToAddCard -> navigateToAddCard()
            is FineDetailEvent.OnCardSelected -> selectCard(event.cardId)
        }

    }

    private fun selectCard(cardId: Int) {
        closeDropdown()
        updateState { copy(selectedCardId = cardId) }

    }


    private fun navigateToAddCard() {
        closeDropdown()
        viewModelScope.launch {
            sendSideEffect(FineDetailEffect.NavigateToAddCard)
        }

    }

    private fun closeDropdown() {
        updateState { copy(showCardsBottomSheet = false) }

    }

    private fun getCards() {
        viewModelScope.launch {
            getCardsUseCase().collect { resource ->

                when (resource) {
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState {
                            copy(
                                showCardsBottomSheet = false
                            )
                        }
                        sendSideEffect(FineDetailEffect.Error(error))

                    }

                    is Resource.Success -> {
                        val cards = resource.data.toPresenter()
                        updateState {
                            copy(
                                cards = cards,
                            )
                        }

                    }

                    Resource.Loading -> Unit
                }

            }

        }
    }

    private fun openCardsBottomSheet() {
        updateState { copy(showCardsBottomSheet = true) }
        getCards()

    }

    private fun startPayment() {

        viewModelScope.launch {
            state.selectedCard?.let { card ->
                state.fine?.let { fine ->
                    payFineUseCase(
                        PayFineRequest(
                            fineId = fine.id,
                            cardId = card.id,
                        )
                    ).collect { resource ->

                        updateState { copy(paymentInProgress = resource.isLoading()) }
                        when (resource) {
                            is Resource.Error -> {
                                val error = resource.error.toGenericString()
                                sendSideEffect(FineDetailEffect.Error(error))
                            }

                            is Resource.Success -> {

                                sendSideEffect(FineDetailEffect.PaymentSuccess)
                            }

                            Resource.Loading -> Unit
                        }
                    }
                }
            }

        }


    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(FineDetailEffect.NavigateBack)
        }
    }


    private fun getFineDetails() {
        viewModelScope.launch {
            getFineDetailsUseCase(fineId).collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                    }

                    is Resource.Success -> {
                        val fine = resource.data.toPresenter()
                        updateState { copy(fine = fine) }


                    }

                    Resource.Loading -> Unit
                }

            }
        }
    }


    @AssistedFactory
    fun interface Factory {
        fun create(
            @Assisted("fineId") fineId: Int,
        ): FineDetailViewModel
    }
}