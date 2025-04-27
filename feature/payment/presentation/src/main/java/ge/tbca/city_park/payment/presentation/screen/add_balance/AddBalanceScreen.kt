@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.payment.presentation.screen.add_balance

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.button.text_button.SecondaryButton
import com.example.core.designsystem.components.button.text_button.TertiaryButton
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.text_field.TextInputField
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.component.card_item.CardItem
import ge.tbca.city_park.payment.presentation.transformation.TransactionAmountTextTransformation

@Composable
fun AddBalanceScreenRoot(
    onShowSnackbar: (String) -> Unit,
    navigateToAddCard: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: AddBalanceViewModel = hiltViewModel(),

    ) {

    val state = viewModel.state
    val scrollState = rememberScrollState()
    val bottomSheetState = rememberModalBottomSheetState()
    val context = LocalContext.current
    val successMessage = stringResource(R.string.balance_added_successfully)
    val cardNotSelectedError = stringResource(R.string.choose_card)

    CollectSideEffect(viewModel.effect) { effect ->
        when (effect) {
            is AddBalanceEffect.Error -> {
                val error = effect.message.getString(context)
                onShowSnackbar(error)
            }

            is AddBalanceEffect.NavigateBack -> navigateBack()
            is AddBalanceEffect.NavigateToAddCard -> navigateToAddCard()
            is AddBalanceEffect.Success -> onShowSnackbar(successMessage)
            AddBalanceEffect.CardNotSelected -> onShowSnackbar(cardNotSelectedError)
        }

    }

    AddBalanceScreen(
        state = state,
        scrollState = scrollState,
        sheetState = bottomSheetState,
        onEvent = viewModel::onEvent
    )

}


@Composable
private fun AddBalanceScreen(
    state: AddBalanceState,
    scrollState: ScrollState,
    sheetState: SheetState,
    onEvent: (AddBalanceEvent) -> Unit,
) {

    val trailingGelSymbol = stringResource(R.string.trailing_gel_symbol)
    val inputErrorText =
        if (state.showTransactionAmountError) stringResource(R.string.please_fill_amount) else null



    PullToRefreshWrapper(
        isRefreshing = state.loading,
        onRefresh = { onEvent(AddBalanceEvent.Retry) }) {


        Column(modifier = Modifier.fillMaxSize()) {
            TopNavigationBar(
                modifier = Modifier.padding(Dimen.appPadding),
                startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                title = stringResource(R.string.add_balance),
                onStartIconClick = { onEvent(AddBalanceEvent.NavigateBack) },
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(Dimen.appPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (state.error != null) {
                    ErrorWrapper(
                        error = state.error.getString(),
                        onRetry = { onEvent(AddBalanceEvent.Retry) }
                    )
                } else {

                    state.selectedCard?.let {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.selected_card),
                            style = TextStyles.bodyLarge,
                            color = AppColors.primary,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(Dimen.size6))

                        CardItem(
                            card = it,
                            enabled = !state.loading,
                            onclick = { onEvent(AddBalanceEvent.ChooseCard) })
                    } ?: run {
                        SecondaryButton(
                            enabled = !state.loading,
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.choose_card),
                            onClick = { onEvent(AddBalanceEvent.ChooseCard) }
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimen.size20))
                    TextInputField(
                        modifier = Modifier.fillMaxWidth(),
                        errorText = inputErrorText,
                        visualTransformation = TransactionAmountTextTransformation(
                            trailingGelSymbol,
                            AppColors.primary.copy(alpha = 0.6f)
                        ),
                        label = stringResource(R.string.enter_transaction_amount),
                        enabled = !state.loading,
                        value = state.transactionAmount,
                        keyboardType = KeyboardType.Number,
                        onTextChanged = {
                            onEvent(AddBalanceEvent.TransactionAmountChanged(it))

                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        buttonSize = ButtonSize.LARGE,
                        enabled = !state.loading,
                        loading = state.transactionInProgress,
                        onClick = { onEvent(AddBalanceEvent.StartTransaction) },
                        text = stringResource(R.string.add_balance),
                        startIcon = Icons.Rounded.AccountBalanceWallet

                    )

                }
            }

        }
    }
    if (state.showBottomSheet)
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onEvent(AddBalanceEvent.CloseBottomSheet) }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Dimen.size12),
                contentPadding = PaddingValues(horizontal = Dimen.size20)
            ) {
                items(state.cards, key = { it.id }) { card ->
                    CardItem(
                        card = card,
                        enabled = !state.loading,
                        onclick = {
                            onEvent(AddBalanceEvent.CardSelected(card.id))
                        }
                    )
                }
                if (state.cards.isNotEmpty()) {
                    item {
                        Divider(
                            text = stringResource(R.string.or)
                        )
                    }
                }
                item {
                    TertiaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        buttonSize = ButtonSize.LARGE,
                        text = stringResource(R.string.add_card),
                        enabled = !state.loading,
                        onClick = {
                            onEvent(AddBalanceEvent.NavigateToAddCard)
                        }
                    )
                }
            }


        }
}


@AppPreview
@Composable
private fun AddBalanceScreenPrev() {
    AppTheme {
        AddBalanceScreen(
            state = AddBalanceState(),
            scrollState = rememberScrollState(),
            sheetState = rememberModalBottomSheetState(),
            onEvent = {}
        )

    }
}