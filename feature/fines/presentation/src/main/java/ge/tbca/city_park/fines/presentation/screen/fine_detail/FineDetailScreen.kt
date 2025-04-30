@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.fines.presentation.screen.fine_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.button.text_button.TertiaryButton
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.additionalColors
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.fines.presentation.R
import ge.tbca.city_park.fines.presentation.component.FineDetailItem
import ge.tbca.city_park.payment.presentation.component.card_bottomsheet.CardBottomSheet
import ge.tbca.city_park.payment.presentation.component.card_item.CardItem


@Composable
fun FineDetailsScreenRoot(
    onShowSnackBar: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onSuccess: () -> Unit,
    navigateToAddCard: () -> Unit,
    fineId: Int
) {

    val viewmodel =
        hiltViewModel<FineDetailViewModel, FineDetailViewModel.Factory>(
            creationCallback = { factory ->
                factory.create(
                    fineId = fineId
                )
            }
        )

    val context = LocalContext.current

    CollectSideEffect(viewmodel.effect) { effect ->
        when (effect) {
            is FineDetailEffect.Error -> {
                val message = effect.error.getString(context)
                onShowSnackBar(message)

            }

            FineDetailEffect.NavigateBack -> onNavigateBack()
            FineDetailEffect.PaymentSuccess -> onSuccess()
            FineDetailEffect.NavigateToAddCard -> navigateToAddCard()
        }
    }


    val scrollState = rememberScrollState()


    FineDetailScreen(
        state = viewmodel.state,
        scrollState = scrollState,
        onEvent = viewmodel::onEvent
    )

}


@Composable
private fun FineDetailScreen(
    state: FineDetailState,
    scrollState: ScrollState,
    onEvent: (FineDetailEvent) -> Unit
) {

    CompositionLocalProvider(LocalContentColor provides AppColors.primary) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(Dimen.appPadding)
        ) {
            TopNavigationBar(
                title = stringResource(R.string.fine_details),
                startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                onStartIconClick = { onEvent(FineDetailEvent.BackButtonClicked) },
            )

            Spacer(modifier = Modifier.height(Dimen.size32))
            state.fine?.let { fine ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(Dimen.size1, AppColors.secondary),
                    shape = RoundedCornerShape(Dimen.roundedCornerMediumSize)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimen.size16),
                    ) {
                        FineDetailItem(
                            icon = Icons.Rounded.DirectionsCar,
                            label = stringResource(R.string.car_number),
                            value = fine.carNumber
                        )

                        Spacer(modifier = Modifier.height(Dimen.size16))

                        FineDetailItem(
                            icon = Icons.Rounded.LocationOn,
                            label = stringResource(R.string.address),
                            value = fine.address
                        )

                        Spacer(modifier = Modifier.height(Dimen.size16))

                        FineDetailItem(
                            icon = Icons.Rounded.Description,
                            label = stringResource(R.string.description),
                            value = fine.description
                        )

                        Spacer(modifier = Modifier.height(Dimen.size16))

                        FineDetailItem(
                            icon = Icons.Rounded.Payments,
                            label = stringResource(R.string.cost_amount),
                            value = stringResource(R.string.cost_with_gel_symbol, fine.price)
                        )

                        Spacer(modifier = Modifier.height(Dimen.size16))


                        FineDetailItem(
                            icon = Icons.Rounded.AccessTime,
                            label = stringResource(R.string.date),
                            value = fine.createdAt
                        )

                        Spacer(modifier = Modifier.height(Dimen.size16))

                        FineDetailItem(
                            icon = if (fine.isPaid) Icons.Rounded.Check else Icons.Rounded.Cancel,
                            label = stringResource(R.string.status),
                            valueColor = if (fine.isPaid) AppColors.additionalColors.success else AppColors.error,
                            value = if (fine.isPaid) stringResource(R.string.paid) else stringResource(
                                R.string.to_be_paid
                            ),
                        )

                    }

                }
            }

            state.error?.let {
                ErrorWrapper(
                    error = it.getString(),
                    enabled = state.isLoading,
                    onRetry = { onEvent(FineDetailEvent.OnRetry) },
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            state.selectedCard?.let { card ->
                CardItem(
                    card = card,
                    enabled = false,
                )
            }

            Spacer(modifier = Modifier.height(Dimen.size12))
            state.fine?.isPaid?.let { paid ->
                if (!paid) {

                    TertiaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.select_card),
                        buttonSize = ButtonSize.LARGE,
                        startIcon = Icons.Rounded.CreditCard,
                        loading = state.paymentInProgress,
                        enabled = !state.isLoading,
                        onClick = { onEvent(FineDetailEvent.SelectCardClicked) }
                    )

                    Spacer(modifier = Modifier.height(Dimen.size12))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.pay_fine),
                        buttonSize = ButtonSize.LARGE,
                        loading = state.paymentInProgress,
                        enabled = state.paymentButtonEnabled,
                        onClick = { onEvent(FineDetailEvent.OnPaymentClicked) }
                    )

                }
            }

            Spacer(modifier = Modifier.height(Dimen.size12))
        }

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = AppColors.primary)
            }
        }

    }

    if (state.showCardsBottomSheet) {
        CardBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onDismiss = { onEvent(FineDetailEvent.CloseCardsDropdown) },
            loading = state.cardsLoading || state.isLoading,
            cards = state.cards,
            onCardClick = { card ->
                onEvent(FineDetailEvent.OnCardSelected(card))
            },

            onAddCardClick = {
                onEvent(FineDetailEvent.NavigateToAddCard)

            }
        )
    }


}


@AppPreview
@Composable
private fun FineDetailScreenPreview() {
    AppTheme {

        FineDetailScreen(
            state = FineDetailState(),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}