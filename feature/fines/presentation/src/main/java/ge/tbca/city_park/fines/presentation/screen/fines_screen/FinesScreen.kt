package ge.tbca.city_park.fines.presentation.screen.fines_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.SearchOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.empty_data_indicator.EmptyDataIndicator
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.theme.additionalColors
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.fines.presentation.R
import ge.tbca.city_park.fines.presentation.model.ParkingFineUi

@Composable
fun FinesScreenRoot(
    navigateToFineDetail: (Int) -> Unit,
    navigateBack: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    viewModel: FinesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is FinesEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is FinesEffect.NavigateToFineDetail -> navigateToFineDetail(effect.fineId)
            is FinesEffect.NavigateBack -> navigateBack()
        }
    }

    FinesScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinesScreen(
    state: FinesState,
    onEvent: (FinesEvent) -> Unit
) {
    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(FinesEvent.Refresh) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Dimen.appPadding),
        ) {
            TopNavigationBar(
                modifier = Modifier.padding(horizontal = Dimen.appPadding),
                startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                title = stringResource(R.string.fines),
                onStartIconClick = { onEvent(FinesEvent.BackButtonClicked) }
            )

            Spacer(modifier = Modifier.height(Dimen.size16))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimen.appPadding)
            ) {
                when {
                    state.error != null -> {
                        ErrorWrapper(
                            error = state.error.getString(),
                            onRetry = { onEvent(FinesEvent.Refresh) },
                            enabled = !state.isLoading
                        )
                    }

                    state.fines.isEmpty() && !state.isLoading -> {
                        EmptyDataIndicator(
                            icon = Icons.Rounded.SearchOff,
                            text = stringResource(R.string.you_dont_have_fines)
                        )
                    }

                    !state.isLoading -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(Dimen.appPadding)
                        ) {
                            items(state.fines) { fine ->
                                FineItem(
                                    fine = fine,
                                    onClick = { onEvent(FinesEvent.FineClicked(fine.id)) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FineItem(
    fine: ParkingFineUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
        color = AppColors.surface,
        onClick = onClick,
        border = BorderStroke(Dimen.size1, AppColors.secondary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.appPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = fine.carNumber,
                    style = TextStyles.titleMedium,
                    color = AppColors.primary
                )

                Spacer(modifier = Modifier.height(Dimen.sizeSmall))

                Text(
                    text = fine.address,
                    style = TextStyles.bodyMedium,
                    color = AppColors.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(Dimen.sizeSmall))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        // TODO GEL symbol string res
                        text = "${fine.price} ₾",
                        style = TextStyles.bodyLarge,
                        color = AppColors.primary
                    )

                    Spacer(modifier = Modifier.width(Dimen.size8))

                    Text(
                        text = if (fine.isPaid) stringResource(R.string.paid) else stringResource(R.string.to_be_paid),
                        style = TextStyles.labelMedium,
                        color = if (fine.isPaid) AppColors.additionalColors.success else AppColors.error,
                        modifier = Modifier.padding(horizontal = Dimen.size8, vertical = Dimen.sizeSmall)
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = AppColors.primary
            )
        }
    }
}

@AppPreview
@Composable
private fun FinesScreenPreview() {
    AppTheme {
        val fines = listOf(
            ParkingFineUi(
                id = 1,
                userId = "user123",
                address = "რუსთაველის გამზირი 12, თბილისი",
                carNumber = "AA-123-BB",
                price = 50.0,
                description = "არასწორი პარკირება ტროტუარზე",
                createdAt = "15:30, 2025-04-28",
                isPaid = false
            ),
            ParkingFineUi(
                id = 2,
                userId = "user456",
                address = "ვაჟა-ფშაველას გამზირი 71, თბილისი",
                carNumber = "AA-999-BB",
                price = 30.0,
                description = "პარკირების დროის გადაცილება",
                createdAt = "02:44, 2025-04-25 ",
                isPaid = true
            ),
            ParkingFineUi(
                id = 3,
                userId = "user789",
                address = "გორგასლის ქუჩა 24, თბილისი",
                carNumber = "GE-555-GE",
                price = 70.0,
                description = "პარკირება აკრძალულ ზონაში",
                createdAt = "02:00, 2025-04-29 ",
                isPaid = false
            )
        )

        FinesScreen(
            state = FinesState(fines = fines),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun FinesScreenEmptyPreview() {
    AppTheme {
        FinesScreen(
            state = FinesState(fines = emptyList()),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun FinesScreenLoadingPreview() {
    AppTheme {
        FinesScreen(
            state = FinesState(isLoading = true),
            onEvent = {}
        )
    }
}