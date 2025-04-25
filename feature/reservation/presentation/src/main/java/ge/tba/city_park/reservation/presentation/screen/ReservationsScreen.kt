package ge.tba.city_park.reservation.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tba.city_park.reservation.presentation.R
import ge.tba.city_park.reservation.presentation.component.ReservationItem
import ge.tba.city_park.reservation.presentation.model.ReservationUi
import ge.tbca.citi_park.core.ui.util.CollectSideEffect

@Composable
fun ReservationsScreenRoot(
    viewModel: ReservationsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            else -> {}
        }
    }

    ReservationsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ReservationsScreen(
    state: ReservationsState,
    onEvent: (ReservationsEvent) -> Unit,
) {
    // TODO pull to refresh
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.reservations),
            endIcon = Icons.Rounded.Add,
            onEndIconClick = { onEvent(ReservationsEvent.AddReservationButtonClicked) }
        )

        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.reservationsList.isEmpty() -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.you_dont_have_reservations),
                    style = TextStyles.bodyLarge,
                    color = AppColors.primary
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = Dimen.size50),
                    verticalArrangement = Arrangement.spacedBy(Dimen.size16)
                ) {
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
                        cost = 1000
                    ),
                    ReservationUi(
                        id = 2,
                        parkingSpotId = 2,
                        zoneCode = "AA123",
                        carNumber = "AA001BB",
                        createdAt = "13:19, 25.04.25",
                        active = false,
                        endedAt = "14:19, 25.04.25",
                        cost = 1000
                    )
                )
            ),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun ReservationsScreenPreviewEmpty() {
    AppTheme {
        ReservationsScreen(
            state = ReservationsState(),
            onEvent = {}
        )
    }
}
