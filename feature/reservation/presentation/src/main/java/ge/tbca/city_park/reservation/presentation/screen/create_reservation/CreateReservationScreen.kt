@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.reservation.presentation.screen.create_reservation

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
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.button.text_button.SecondaryButton
import com.example.core.designsystem.components.dialog.BaseAlertDialog
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
import ge.tbca.city_park.reservation.presentation.R
import ge.tbca.city_park.cars.presentation.component.CarsBottomSheet
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.cars.presentation.component.car_item.CarItem

@Composable
fun CreateReservationScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit,
    navigateToMap: () -> Unit,
    navigateToAddCar: () -> Unit,
    navigateToAddBalance: () -> Unit,
    viewModel: CreateReservationViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val successText = stringResource(R.string.reservation_added)
    val noCarSelectedError = stringResource(R.string.select_car)

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is CreateReservationEffect.Success -> {
                onShowSnackBar(successText)
                navigateBack()
            }

            is CreateReservationEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is CreateReservationEffect.NavigateBack -> navigateBack()
            is CreateReservationEffect.NavigateToMap -> navigateToMap()
            is CreateReservationEffect.NavigateToAddCar -> navigateToAddCar()
            is CreateReservationEffect.NoCarSelected -> onShowSnackBar(noCarSelectedError)
            is CreateReservationEffect.NavigateToAddBalance -> navigateToAddBalance()
        }
    }

    CreateReservationScreen(
        state = viewModel.state,
        scrollState = scrollState,
        sheetState = sheetState,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateReservationScreen(
    state: CreateReservationState,
    scrollState: ScrollState,
    sheetState: SheetState,
    onEvent: (CreateReservationEvent) -> Unit
) {

    val zoneCodeError =
        if (state.showZoneCodeError) stringResource(R.string.enter_valid_zone_code) else null

    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(CreateReservationEvent.Retry) }) {

        Column(modifier = Modifier.fillMaxSize()) {
            TopNavigationBar(
                modifier = Modifier.padding(Dimen.appPadding),
                title = stringResource(R.string.start_parking),
                startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                onStartIconClick = { onEvent(CreateReservationEvent.BackButtonClicked) }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState, enabled = !state.isLoading)
                    .padding(Dimen.appPadding)
            ) {

                Spacer(modifier = Modifier.height(Dimen.size16))

                if (state.error != null) {
                    ErrorWrapper(
                        error = state.error.getString(),
                        onRetry = { onEvent(CreateReservationEvent.Retry) },
                        enabled = !state.isLoading
                    )
                } else {
                    state.selectedCar?.let {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.selected_car), color = AppColors.primary,
                            style = TextStyles.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(Dimen.size6))

                        CarItem(
                            car = it,
                            onClick = { onEvent(CreateReservationEvent.ShowBottomSheet) })
                    } ?: run {
                        SecondaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !state.isLoading,
                            text = stringResource(R.string.select_car),
                            onClick = { onEvent(CreateReservationEvent.ShowBottomSheet) }
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimen.size20))

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
                        startIcon = Icons.Rounded.LocationOn,
                        onClick = { onEvent(CreateReservationEvent.ChooseOnMapButtonClicked) }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !state.isLoading && state.selectedCarId != null,
                        loading = state.isLoading,
                        buttonSize = ButtonSize.LARGE,
                        text = stringResource(R.string.start_parking),
                        onClick = { onEvent(CreateReservationEvent.CreateReservationButtonClicked) }
                    )
                }
            }
        }
    }

    if (state.showBottomSheet)
        CarsBottomSheet(
            onDismissRequest = {
                onEvent(CreateReservationEvent.CloseBottomSheet)
            },
            onCarSelected = {
                onEvent(CreateReservationEvent.CarSelected(it))
            },
            onAddCar = {
                onEvent(CreateReservationEvent.NavigateToAddCar)
            },
            cars = state.carsList,
            sheetState = sheetState,
        )

    if (state.showInsufficientBalanceDialog) {
        BaseAlertDialog(
            onDismiss = { onEvent(CreateReservationEvent.DismissAlertDialog) },
            setDismissible = true,
            onPositiveButtonClick = {
                onEvent(CreateReservationEvent.NavigateToAddBalance)
            },
            onNegativeButtonClick = {
                onEvent(CreateReservationEvent.DismissAlertDialog)
            },
            positiveButtonText = stringResource(R.string.add_balance),
            negativeButtonText = stringResource(R.string.cancel),
            title = stringResource(R.string.insufficient_balance),
            message = stringResource(R.string.please_add_balance_to_start_parking)
        )
    }

}

@AppPreview
@Composable
private fun CreateReservationScreenPreview() {
    AppTheme {
        CreateReservationScreen(
            state = CreateReservationState(),
            scrollState = rememberScrollState(),
            sheetState = rememberModalBottomSheetState(),
            onEvent = {}
        )
    }
}