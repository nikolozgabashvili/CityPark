package ge.tbca.city_park.messaging.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.dialog.BaseAlertDialog
import com.example.core.designsystem.components.empty_data_indicator.EmptyDataIndicator
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.messaging.presentation.R
import ge.tbca.city_park.messaging.presentation.component.NotificationItem
import ge.tbca.city_park.messaging.presentation.model.NotificationUi

@Composable
fun NotificationsScreenRoot(
    navigateBack: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    viewModel: NotificationsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is NotificationsEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is NotificationsEffect.NavigateBack -> navigateBack()
        }
    }

    NotificationsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationsScreen(
    state: NotificationsState,
    onEvent: (NotificationsEvent) -> Unit
) {
    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(NotificationsEvent.Refresh) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Dimen.appPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopNavigationBar(
                modifier = Modifier.padding(horizontal = Dimen.appPadding),
                startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                title = stringResource(R.string.notifications_history),
                onStartIconClick = { onEvent(NotificationsEvent.BackButtonClicked) },
                endIcon = if (state.notificationsList.isNotEmpty()) Icons.Default.Delete else null,
                onEndIconClick = { onEvent(NotificationsEvent.DeleteAllNotificationsClicked) }
            )

            Spacer(modifier = Modifier.height(Dimen.size16))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = Dimen.appPadding)
            ) {
                if (state.noNotifications) {
                    item {
                        EmptyDataIndicator(
                            icon = Icons.Rounded.Notifications,
                            text = "შეტყობინებები არ გაქვს"
                        )
                    }
                } else if (state.error != null) {
                    item {
                        val error = state.error.getString()
                        ErrorWrapper(
                            error = error,
                            onRetry = { onEvent(NotificationsEvent.Refresh) },
                            enabled = !state.isLoading,
                        )
                    }
                } else if (state.notificationsList.isNotEmpty()) {
                    items(state.notificationsList) { notification ->
                        NotificationItem(
                            modifier = Modifier.padding(vertical = Dimen.size6),
                            notification = notification,
                            hasDeleteIcon = true,
                            onDeleteClick = {
                                onEvent(
                                    NotificationsEvent.DeleteNotificationClicked(notification.id)
                                )
                            }
                        )
                    }
                }
            }

            if (state.showDeleteAllNotificationsDialog) {
                BaseAlertDialog(
                    onDismiss = { onEvent(NotificationsEvent.DismissDeleteAllNotificationsDialog) },
                    onPositiveButtonClick = {
                        onEvent(NotificationsEvent.DeleteAllNotifications)
                    },
                    onNegativeButtonClick = { onEvent(NotificationsEvent.DismissDeleteAllNotificationsDialog) },
                    positiveButtonText = stringResource(R.string.yes),
                    negativeButtonText = stringResource(R.string.no),
                    title = stringResource(R.string.delete),
                    message = stringResource(R.string.do_you_really_wish_to_delete_all_notifications)
                )
            }
        }
    }
}

@AppPreview
@Composable
private fun NotificationsScreenPreview() {
    AppTheme {
        NotificationsScreen(
            state = NotificationsState(
                notificationsList = listOf(
                    NotificationUi(
                        id = 1,
                        userId = 123,
                        title = "Parking",
                        message = "Started parking on car AA001BB in zone SA002",
                        createdAt = "19:22, 28.04.25"
                    ),
                    NotificationUi(
                        id = 2,
                        userId = 123,
                        title = "Parking",
                        message = "Started parking on car AA001BB in zone GL003",
                        createdAt = "20:20, 28.04.25"
                    )
                )
            ),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun NotificationsScreenPreviewNoNotifications() {
    AppTheme {
        NotificationsScreen(
            state = NotificationsState(
                noNotifications = true
            ),
            onEvent = {}
        )
    }
}