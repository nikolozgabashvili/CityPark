package ge.tbca.city_park.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.home.presentation.screen.HomeScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeNavGraph(
    navigateToCars: () -> Unit,
    onShowSnackBar: (String) -> Unit,
) {

    composable<HomeScreenRoute> {
        HomeScreenRoot(
            navigateToCars = navigateToCars,
        )
    }


}

@Serializable
data object HomeScreenRoute