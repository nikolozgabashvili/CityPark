package ge.tbca.city_park.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
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
    navigateToAddCar:() -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            HomeEffect.NavigateToAddCar -> navigateToAddCar()
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
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.home)
        )

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.cars.isEmpty() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.no_cars),
                        style = TextStyles.bodyLarge,
                        color = AppColors.primary
                    )

                    Spacer(modifier = Modifier.height(Dimen.size16))

                    PrimaryButton(
                        onClick = { onEvent(HomeEvent.AddCarButtonClicked) },
                        text = stringResource(R.string.add_car),
                        buttonSize = ButtonSize.LARGE
                    )
                }
            }

            else -> {

                PrimaryButton(
                    modifier = Modifier.fillMaxWidth().padding(top = Dimen.size16),
                    onClick = { onEvent(HomeEvent.AddCarButtonClicked) },
                    text = stringResource(R.string.add_car),
                    buttonSize = ButtonSize.LARGE,
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = Dimen.size16),
                    verticalArrangement = Arrangement.spacedBy(Dimen.size8)
                ) {


                    items(items=state.cars, key = { it.id }) { car ->
                        CarItem(
                            car = car,
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
            state = HomeState(),
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