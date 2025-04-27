package ge.tbca.city_park.user.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.user.presentation.screen.ProfileScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.profileNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit,
    navigateToChangePassword: () -> Unit
) {
    composable<ProfileScreenRoute> {
        ProfileScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateBack = navigateBack,
            navigateToChangePassword = navigateToChangePassword
        )
    }
}

@Serializable
data object ProfileScreenRoute