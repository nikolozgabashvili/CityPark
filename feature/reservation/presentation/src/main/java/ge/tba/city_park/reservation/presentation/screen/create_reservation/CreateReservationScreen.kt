package ge.tba.city_park.reservation.presentation.screen.create_reservation

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.button.text_button.SecondaryButton
import com.example.core.designsystem.components.button.text_button.TertiaryButton
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.text_field.TextInputField
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tba.city_park.reservation.presentation.R
import ge.tba.city_park.reservation.presentation.component.CarDropDownMenu
import ge.tbca.citi_park.core.ui.util.CollectSideEffect

@Composable
fun CreateReservationScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit,
    navigateToMap: () -> Unit,
    navigateToAddCar: () -> Unit,
    viewModel: CreateReservationViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is CreateReservationEffect.Success -> {
                val successText = context.getString(R.string.reservation_added)
                onShowSnackBar(successText)
            }

            is CreateReservationEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is CreateReservationEffect.NavigateBack -> navigateBack()

            is CreateReservationEffect.NavigateToMap -> navigateToMap()

            is CreateReservationEffect.NavigateToAddCar -> navigateToAddCar()
        }
    }

    CreateReservationScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateReservationScreen(
    state: CreateReservationState,
    scrollState: ScrollState,
    onEvent: (CreateReservationEvent) -> Unit
) {

    val zoneCodeError =
        if (state.showZoneCodeError) stringResource(R.string.enter_valid_zone_code) else null

    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(CreateReservationEvent.Retry) }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState, enabled = !state.isLoading)
                .padding(Dimen.appPadding)
        ) {
            TopNavigationBar(
                title = stringResource(R.string.create_reservation),
                startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                onStartIconClick = { onEvent(CreateReservationEvent.BackButtonClicked) }
            )

            Spacer(modifier = Modifier.height(Dimen.size16))

            Column {
                TertiaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isLoading,
                    text = "აირჩიე ავტომობილი",
                    onClick = { onEvent(CreateReservationEvent.ShowDropDown) }
                )

                Spacer(modifier = Modifier.height(Dimen.size8))

                CarDropDownMenu(
                    items = state.carsList,
                    expanded = state.showDropDown,
                    onDismiss = { onEvent(CreateReservationEvent.CloseDropDown) },
                    onCardClick = { onEvent(CreateReservationEvent.CarSelected(it)) },
                    onAdditionalItemClick = { onEvent(CreateReservationEvent.NavigateToAddCar) }
                )
            }

            Spacer(modifier = Modifier.height(Dimen.size16))

            TextInputField(
                modifier = Modifier.fillMaxWidth(),
                value = state.zoneCode,
                enabled = !state.isLoading,
                errorText = zoneCodeError,
                label = stringResource(R.string.zone_code),
                imeAction = ImeAction.Next,
                onTextChanged = { onEvent(CreateReservationEvent.ZoneCodeChanged(it)) }
            )

            Spacer(modifier = Modifier.height(Dimen.size32))

            Divider(text = stringResource(R.string.or_choose_place_on_map))

            Spacer(modifier = Modifier.height(Dimen.size32))

            SecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading,
                text = stringResource(R.string.choose_on_map),
                onClick = { onEvent(CreateReservationEvent.ChooseOnMapButtonClicked) }
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading,
                loading = state.isLoading,
                buttonSize = ButtonSize.LARGE,
                text = stringResource(R.string.create_reservation),
                onClick = { onEvent(CreateReservationEvent.CreateReservationButtonClicked) }
            )
        }
    }
}

@AppPreview
@Composable
private fun CreateReservationScreenPreview() {
    AppTheme {
        CreateReservationScreen(
            state = CreateReservationState(),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}