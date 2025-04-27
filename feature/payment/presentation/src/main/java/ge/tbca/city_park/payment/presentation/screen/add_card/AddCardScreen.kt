package ge.tbca.city_park.payment.presentation.screen.add_card

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.text_field.TextInputField
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.transformation.ChunkedTextVisualTransformation

@Composable
fun AddCardScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit,
    viewModel: AddCardViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val successMessage = stringResource(R.string.card_added_successfully)

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is AddCardEffect.Error -> {
                val error = effect.message.getString(context)
                onShowSnackBar(error)
            }

            is AddCardEffect.NavigateBack -> navigateBack()
            is AddCardEffect.Success -> {
                onShowSnackBar(successMessage)
                navigateBack()
            }
        }
    }

    AddCardScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun AddCardScreen(
    state: AddCardState,
    scrollState: ScrollState,
    onEvent: (AddCardEvent) -> Unit
) {

    val cardNumberError =
        if (state.showCardNumberError) stringResource(R.string.enter_correct_card_number) else null
    val expireDateError =
        if (state.showExpireDateError) stringResource(R.string.enter_valid_expire_date) else null
    val showCvvError = if (state.showCvvError) stringResource(R.string.enter_valid_cvv) else null
    val showCardHolderNameError =
        if (state.showCardHolderNameError) stringResource(R.string.enter_valid_name) else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState, enabled = !state.isLoading)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.add_card),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(AddCardEvent.BackButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.cardNumber,
            enabled = !state.isLoading,
            errorText = cardNumberError,
            label = stringResource(R.string.card_number),
            visualTransformation = ChunkedTextVisualTransformation(
                chunkSize = 4,
                separator = " "
            ),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            onTextChanged = { onEvent(AddCardEvent.CardNumberChanged(it)) }
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimen.size8)
        ) {
            TextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = state.expireDate,
                enabled = !state.isLoading,
                errorText = expireDateError,
                visualTransformation = ChunkedTextVisualTransformation(
                    chunkSize = 2,
                    separator = "/"
                ),
                label = stringResource(R.string.expire_date),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                onTextChanged = { onEvent(AddCardEvent.ExpireDateChanged(it)) }
            )

            TextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = state.cvv,
                enabled = !state.isLoading,
                errorText = showCvvError,
                label = stringResource(R.string.cvv),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                onTextChanged = { onEvent(AddCardEvent.CvvChanged(it)) }
            )
        }

        Spacer(modifier = Modifier.height(Dimen.size8))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.cardHolderName,
            enabled = !state.isLoading,
            errorText = showCardHolderNameError,
            label = stringResource(R.string.cardholder_name),
            imeAction = ImeAction.Done,
            onTextChanged = { onEvent(AddCardEvent.CardHolderNameChanged(it)) }
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            loading = state.isLoading,
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.save_button),
            onClick = { onEvent(AddCardEvent.SaveCardButtonClicked) }
        )
    }
}

@AppPreview
@Composable
private fun AddCreditCardScreenPreview() {
    AppTheme {
        AddCardScreen(
            state = AddCardState(),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}
