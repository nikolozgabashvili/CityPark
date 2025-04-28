@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.payment.presentation.screen.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AddCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.dialog.BaseAlertDialog
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.components.empty_data_indicator.EmptyDataIndicator
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.payment.domain.model.CardType
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.component.card_item.CardItem
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

@Composable
fun CardsScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateToAddCard: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: CardsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is CardsEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is CardsEffect.NavigateBack -> navigateBack()

            is CardsEffect.NavigateToAddCard -> navigateToAddCard()
        }
    }

    CardsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CardsScreen(
    state: CardsState,
    onEvent: (CardsEvent) -> Unit
) {
    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(CardsEvent.Refresh) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimen.appPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimen.size16)
            ) {
                TopNavigationBar(
                    title = stringResource(R.string.credit_cards),
                    startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                    onStartIconClick = { onEvent(CardsEvent.NavigateBack) }
                )

                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onEvent(CardsEvent.AddCardButtonClicked) },
                    text = stringResource(R.string.add_card),
                    buttonSize = ButtonSize.LARGE,
                )

                Divider(text = stringResource(R.string.your_cards))
            }

            Spacer(modifier = Modifier.height(Dimen.size16))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Dimen.size16)
            ) {
                if (state.noCards) {
                    item {
                        EmptyDataIndicator(
                            icon = Icons.Rounded.AddCard,
                            text = stringResource(R.string.you_havent_added_any_cards)
                        )
                    }
                } else if (state.error != null) {
                    item {
                        val error = state.error.getString()
                        ErrorWrapper(
                            error = error,
                            enabled = !state.isLoading,
                            onRetry = { onEvent(CardsEvent.Refresh) },
                        )
                    }
                } else if (state.cardsList.isNotEmpty()) {

                    items(items = state.cardsList, key = { it.id }) { card ->
                        CardItem(
                            card = card,
                            hasDeleteIcon = true,
                            onDeleteClick = { onEvent(CardsEvent.DeleteCardClicked(card.id)) },
                        )
                    }
                }
            }

            if (state.showDeleteCardDialog) {
                BaseAlertDialog(
                    onDismiss = { onEvent(CardsEvent.DismissDeleteCardDialog) },
                    onPositiveButtonClick = {
                        onEvent(CardsEvent.DeleteCard)
                    },
                    onNegativeButtonClick = { onEvent(CardsEvent.DismissDeleteCardDialog) },
                    positiveButtonText = stringResource(R.string.yes),
                    negativeButtonText = stringResource(R.string.no),
                    title = stringResource(R.string.delete),
                    message = stringResource(R.string.do_you_really_wish_to_delete_card),
                )
            }
        }
    }
}

@AppPreview
@Composable
private fun CardsScreenPreview() {
    AppTheme {
        CardsScreen(
            state = CardsState(
                cardsList = listOf(
                    CreditCardUi(
                        id = 1,
                        cardNumber = "1234 1234 1234 1234",
                        holderName = "John Doe",
                        balance = 100.0,
                        expirationDate = "12/28",
                        cvv = "123",
                        cardType = CardType.MASTERCARD
                    ),
                    CreditCardUi(
                        id = 2,
                        cardNumber = "1234 1234 1234 1234",
                        holderName = "John Doe",
                        balance = 100.0,
                        expirationDate = "12/28",
                        cvv = "123",
                        cardType = CardType.VISA
                    )
                )
            ),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun CardsScreenPreviewDialog() {
    AppTheme {
        CardsScreen(
            state = CardsState(showDeleteCardDialog = true),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun CardsScreenPreviewNoCars() {
    AppTheme {
        CardsScreen(
            state = CardsState(noCards = true),
            onEvent = {}
        )
    }
}