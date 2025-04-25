@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.home.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.image.IconWithBackground
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.cars.presentation.model.CarUi
import ge.tbca.city_park.home.presentation.R
import ge.tbca.city_park.home.presentation.component.car_item.CarItem

@Composable
fun HomeScreenRoot(
    navigateToAddCar: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            HomeEffect.NavigateToAddCar -> navigateToAddCar()
            is HomeEffect.Error -> {
                val message = effect.error.getString(context)
                onShowSnackBar(message)

            }
        }
    }

    HomeScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}


@Composable
private fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {


    PullToRefreshWrapper(
        isRefreshing = state.carsLoading,
        onRefresh = { onEvent(HomeEvent.Refresh) },
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(horizontal = Dimen.appPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimen.size16)
            ) {
                TopNavigationBar(
                    title = stringResource(R.string.home)
                )


                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onEvent(HomeEvent.AddCarButtonClicked) },
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimen.size20)
                                .background(
                                    AppColors.surface, shape = RoundedCornerShape(
                                        Dimen.roundedCornerMediumSize
                                    )
                                )
                                .padding(vertical = Dimen.size8),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Dimen.size12)
                        ) {
                            IconWithBackground(
                                icon = Icons.Rounded.DirectionsCar
                            )

                            Text(
                                style = TextStyles.bodyMedium,
                                fontWeight = FontWeight.Medium,
                                color = AppColors.onBackground,
                                text = stringResource(R.string.no_cars)
                            )

                        }
                    }

                } else if (state.error != null) {
                    item {
                        val error = state.error.getString()
                        ErrorWrapper(
                            error = error,
                            onRetry = { onEvent(HomeEvent.Refresh) },
                        )
                    }

                } else if (state.cars.isNotEmpty()) {

                    items(state.cars) { car ->
                        CarItem(
                            car = car,
                            modifier = Modifier.padding(vertical = Dimen.size6),
                            onClick = { onEvent(HomeEvent.CarClicked(car.id)) }
                        )
                    }
                }
            }
        }
    }

}

@AppPreview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            state = HomeState(
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
private fun HomeScreenPreviewNoCars() {
    AppTheme {
        HomeScreen(
            state = HomeState(noCars = true),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun HomeScreenPreviewLoading() {
    AppTheme {
        HomeScreen(
            state = HomeState(isLoading = true),
            onEvent = {}
        )
    }
}