package ge.tbca.city_park.messaging.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.messaging.presentation.screen.NotificationsScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.notificationsNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit
) {
    composable<NotificationsScreenRoute> {
        NotificationsScreenRoot(
            navigateBack = navigateBack,
            onShowSnackBar = onShowSnackBar
        )
    }
}

@Serializable
data object NotificationsScreenRoute