@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.cars.presentation.screen.my_cars

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.components.empty_data_indicator.EmptyDataIndicator
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.cars.presentation.R
import ge.tbca.city_park.cars.presentation.component.car_item.CarItem
import ge.tbca.city_park.cars.presentation.model.CarUi

@Composable
fun CarsScreenRoot(
    navigateToAddCar: () -> Unit,
    navigateBack: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    viewModel: CarsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is CarsEffect.NavigateToAddCar -> navigateToAddCar()

            is CarsEffect.Error -> {
                val message = effect.error.getString(context)
                onShowSnackBar(message)
            }

            is CarsEffect.NavigateBack -> navigateBack()
        }
    }

    CarsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}


@Composable
private fun CarsScreen(
    state: CarsState,
    onEvent: (CarsEvent) -> Unit
) {


    PullToRefreshWrapper(
        isRefreshing = state.carsLoading,
        onRefresh = { onEvent(CarsEvent.Refresh) },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Dimen.appPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(horizontal = Dimen.appPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimen.size16)
            ) {
                TopNavigationBar(
                    startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                    title = stringResource(R.string.cars),
                    onStartIconClick = {
                        onEvent(CarsEvent.BackButtonClicked)
                    }
                )


                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onEvent(CarsEvent.AddCarButtonClicked) },
                    text = stringResource(R.string.add_car),
                    buttonSize = ButtonSize.LARGE,
                )

                Divider(text = stringResource(R.string.your_cars))

            }

            Spacer(modifier = Modifier.height(Dimen.size6))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = Dimen.appPadding)
            ) {

                if (state.noCars) {

                    item {
                        Spacer(modifier = Modifier.height(Dimen.size16))
                    }
                    item {
                        EmptyDataIndicator(
                            icon = Icons.Rounded.DirectionsCar,
                            text = stringResource(R.string.no_cars)
                        )
                    }

                } else if (state.error != null) {
                    item {
                        val error = state.error.getString()
                        ErrorWrapper(
                            error = error,
                            onRetry = { onEvent(CarsEvent.Refresh) },
                        )
                    }

                } else if (state.cars.isNotEmpty()) {

                    items(state.cars) { car ->
                        CarItem(
                            car = car,
                            modifier = Modifier.padding(vertical = Dimen.size6),
                            onClick = { onEvent(CarsEvent.CarClicked(car.id)) }
                        )
                    }
                }
            }
        }
    }

}

@AppPreview
@Composable
private fun CarsScreenPreview() {
    AppTheme {
        CarsScreen(
            state = CarsState(
                cars = listOf(
                    CarUi(
                        id = 1,
                        carName = "Tesla",
                        plateNumber = "AA123BB"
                    ),
                    CarUi(
                        id = 2,
                        carName = "Mercedes",
                        plateNumber = "CC456DD"
                    ),
                    CarUi(
                        id = 3,
                        plateNumber = "EE789FF"
                    )
                )
            ),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun CarsScreenPreviewNoCars() {
    AppTheme {
        CarsScreen(
            state = CarsState(noCars = true),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun CarsScreenPreviewLoading() {
    AppTheme {
        CarsScreen(
            state = CarsState(isLoading = true),
            onEvent = {}
        )
    }
}