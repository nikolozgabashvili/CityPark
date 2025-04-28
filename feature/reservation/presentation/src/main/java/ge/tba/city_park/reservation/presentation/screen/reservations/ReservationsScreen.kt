@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tba.city_park.reservation.presentation.screen.reservations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.HistoryEdu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.empty_data_indicator.EmptyDataIndicator
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tba.city_park.reservation.presentation.R
import ge.tba.city_park.reservation.presentation.component.ReservationItem
import ge.tba.city_park.reservation.presentation.model.ReservationUi
import ge.tbca.city_park.core.ui.util.CollectSideEffect

@Composable
fun ReservationsScreenRoot(
    navigateToCreateReservation: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    viewModel: ReservationsViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val scrollState = rememberLazyListState()

    CollectSideEffect(flow = viewModel.effect) { effect ->

        when (effect) {
            is ReservationsEffect.Error -> {
                val error = effect.message.getString(context)
                onShowSnackBar(error)
            }

            is ReservationsEffect.NavigateToAddReservation -> {
                navigateToCreateReservation()
            }

            ReservationsEffect.ReservationsRefreshed -> {
                scrollState.animateScrollToItem(0)
            }

        }

    }

    ReservationsScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ReservationsScreen(
    state: ReservationsState,
    scrollState: LazyListState,
    onEvent: (ReservationsEvent) -> Unit,
) {
    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(ReservationsEvent.Refresh) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopNavigationBar(
                modifier = Modifier.padding(Dimen.appPadding),
                title = stringResource(R.string.parking_history),
                endIcon = Icons.Rounded.Add,
                onEndIconClick = { onEvent(ReservationsEvent.AddReservationButtonClicked) }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Dimen.size16),
                state = scrollState,
                contentPadding = PaddingValues(horizontal = Dimen.appPadding),
                verticalArrangement = Arrangement.spacedBy(Dimen.size16)
            ) {
                if (state.noReservations) {
                    item {
                        EmptyDataIndicator(
                            icon = Icons.Rounded.HistoryEdu,
                            text = stringResource(R.string.you_dont_have_reservations)
                        )
                    }
                } else if (state.error != null) {
                    item {
                        val error = state.error.getString()
                        ErrorWrapper(
                            error = error,
                            enabled = !state.isLoading,
                            onRetry = { onEvent(ReservationsEvent.Refresh) },
                        )
                    }
                } else if (state.reservationsList.isNotEmpty()) {

                    items(items = state.reservationsList, key = { it.id }) { reservation ->
                        ReservationItem(reservation = reservation)
                    }

                }
            }


        }
    }
}


@AppPreview
@Composable
private fun ReservationsScreenPreview() {
    AppTheme {
        ReservationsScreen(
            state = ReservationsState(
                reservationsList = listOf(
                    ReservationUi(
                        id = 1,
                        parkingSpotId = 1,
                        zoneCode = "AA123",
                        carNumber = "AA001BB",
                        createdAt = "13:19, 25.04.25",
                        active = true,
                        cost = 1000.0
                    ),
                    ReservationUi(
                        id = 2,
                        parkingSpotId = 2,
                        zoneCode = "AA123",
                        carNumber = "AA001BB",
                        createdAt = "13:19, 25.04.25",
                        active = false,
                        endedAt = "14:19, 25.04.25",
                        cost = 1000.0
                    )
                )
            ),
            onEvent = {},
            scrollState = rememberLazyListState()
        )
    }
}

@AppPreview
@Composable
private fun ReservationsScreenPreviewEmpty() {
    AppTheme {
        ReservationsScreen(
            state = ReservationsState(),
            onEvent = {},
            scrollState = rememberLazyListState()
        )
    }
}
