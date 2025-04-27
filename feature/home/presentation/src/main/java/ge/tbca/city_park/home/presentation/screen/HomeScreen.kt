@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.home.presentation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.action_card.ActionCard
import com.example.core.designsystem.components.dialog.BaseAlertDialog
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.home.presentation.R
import ge.tbca.city_park.home.presentation.component.ActiveReservationCard
import ge.tbca.city_park.user.presentation.component.UserBalanceCard

@Composable
fun HomeScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateToCars: () -> Unit,
    navigateToCards: () -> Unit,
    navigateToAddBalance: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToAddReservation: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(viewModel.effect) { effect ->
        when (effect) {
            is HomeEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is HomeEffect.NavigateToAddBalance -> navigateToAddBalance()

            is HomeEffect.NavigateToProfile -> navigateToProfile()

            is HomeEffect.NavigateToCars -> navigateToCars()

            is HomeEffect.NavigateToAddReservation -> navigateToAddReservation()

            is HomeEffect.NavigateToCards -> navigateToCards()
        }

    }

    HomeScreen(
        state = state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    state: HomeState,
    scrollState: ScrollState,
    onEvent: (HomeEvent) -> Unit,
) {
    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(HomeEvent.Refresh) }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            TopNavigationBar(
                modifier = Modifier.padding(Dimen.appPadding),
                title = stringResource(R.string.home),
                endIcon = Icons.Rounded.Person,
                onEndIconClick = { onEvent(HomeEvent.NavigateToProfile) },
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState, enabled = !state.isLoading)
                    .padding(horizontal = Dimen.appPadding),
                verticalArrangement = Arrangement.spacedBy(Dimen.size20)
            ) {
                state.error?.let {
                    ErrorWrapper(
                        error = state.error.getString(),
                        enabled = state.clickEnabled,
                        onRetry = { onEvent(HomeEvent.Refresh) }
                    )
                }


                state.userBalance?.let {
                    UserBalanceCard(
                        balance = state.userBalance,
                        enabled = state.clickEnabled,
                        onAddBalanceClick = {
                            onEvent(HomeEvent.NavigateToAddBalance)
                        }
                    )
                }

                state.activeReservation?.let { reservation ->
                    ActiveReservationCard(
                        enabled = state.clickEnabled,
                        reservation = reservation,
                        onFinishRequest = { onEvent(HomeEvent.OnFinishRequest) },
                    )
                } ?: run {
                    ActionCard(
                        modifier = Modifier.fillMaxWidth(),
                        loading = state.isLoading,
                        text = stringResource(R.string.start_parking),
                        startIcon = Icons.Default.LocalParking,
                        onclick = { onEvent(HomeEvent.NavigateToAddReservation) }
                    )
                }

                ActionCard(
                    text = stringResource(R.string.my_cars),
                    enabled = state.clickEnabled,
                    loading = state.isLoading,
                    startIcon = Icons.Default.DirectionsCar,
                    onclick = { onEvent(HomeEvent.NavigateToCars) }
                )

                ActionCard(
                    text = stringResource(R.string.my_cards),
                    enabled = state.clickEnabled,
                    loading = state.isLoading,
                    startIcon = Icons.Default.CreditCard,
                    onclick = { onEvent(HomeEvent.NavigateToCards) }
                )
            }
        }

        if (state.showParkingFinishDialog) {
            BaseAlertDialog(
                onDismiss = { onEvent(HomeEvent.DismissParkingDialog) },
                onPositiveButtonClick = { onEvent(HomeEvent.FinishParking) },
                onNegativeButtonClick = { onEvent(HomeEvent.DismissParkingDialog) },
                positiveButtonText = stringResource(R.string.yes),
                negativeButtonText = stringResource(R.string.no),
                title = stringResource(R.string.finish),
                message = stringResource(R.string.finish_parking_message),
            )
        }
    }
}

@AppPreview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            state = HomeState(),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}