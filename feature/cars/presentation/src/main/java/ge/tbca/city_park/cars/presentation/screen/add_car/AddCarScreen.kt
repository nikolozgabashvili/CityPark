package ge.tbca.city_park.cars.presentation.screen.add_car

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
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
import com.example.core.designsystem.components.checkbox.PrimaryCheckbox
import com.example.core.designsystem.components.text_field.TextInputField
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.cars.presentation.R

@Composable
fun AddCarScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit,
    viewModel: AddCarViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when(effect) {
            is AddCarEffect.ShowSnackbar -> {
                val error = effect.message.getString(context)
                onShowSnackBar(error)
            }
            is AddCarEffect.NavigateBack -> navigateBack()
        }
    }

    AddCarScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun AddCarScreen(
    state: AddCarState,
    scrollState: ScrollState,
    onEvent: (AddCarEvent) -> Unit
) {

    val carNameError = if (state.showCarNameError) stringResource(R.string.enter_car_name) else null
    val plateNumberError =
        if (state.showPlateNumberError) stringResource(R.string.enter_valid_plate_number) else null
    val ownerPersonalNumberError =
        if (state.showOwnerPersonalNumberError) stringResource(R.string.enter_valid_personal_number) else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState, enabled = !state.isLoading)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.add_car),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(AddCarEvent.BackButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.plateNumber,
            enabled = !state.isLoading,
            errorText = plateNumberError,
            label = stringResource(R.string.car_plate_number),
            imeAction = ImeAction.Next,
            onTextChanged = { onEvent(AddCarEvent.PlateNumberChanged(it)) }
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.ownerPersonalNumber,
            enabled = !state.isLoading,
            errorText = ownerPersonalNumberError,
            label = stringResource(R.string.owner_personal_number),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number,
            onTextChanged = { onEvent(AddCarEvent.OwnerPersonalNumberChanged(it)) }
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        PrimaryCheckbox(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.add_car_name),
            isChecked = state.isChecked,
            isLoading = state.isLoading,
            onClick = { onEvent(AddCarEvent.CheckBoxChanged) }
        )

        if (state.isChecked) {
            TextInputField(
                modifier = Modifier.fillMaxWidth(),
                value = state.carName,
                enabled = !state.isLoading,
                errorText = carNameError,
                label = stringResource(R.string.car_name),
                imeAction = ImeAction.Next,
                onTextChanged = { onEvent(AddCarEvent.CarNameChanged(it)) }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            loading = state.isLoading,
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.save_car),
            onClick = { onEvent(AddCarEvent.SaveCarButtonClicked) }
        )
    }
}

@AppPreview
@Composable
private fun AddCarScreenPreview() {
    AppTheme {
        AddCarScreen(
            state = AddCarState(isChecked = true),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun AddCarScreenPreviewUnselected() {
    AppTheme {
        AddCarScreen(
            state = AddCarState(),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun AddCarScreenPreviewWithErrors() {
    AppTheme {
        AddCarScreen(
            state = AddCarState(
                isChecked = true,
                showCarNameError = true,
                showPlateNumberError = true,
                showOwnerPersonalNumberError = true
            ),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}