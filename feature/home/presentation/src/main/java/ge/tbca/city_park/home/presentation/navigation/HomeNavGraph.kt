package ge.tbca.city_park.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.home.presentation.screen.HomeScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeNavGraph(
    navigateToAddCar: () -> Unit,
) {

    composable<HomeScreenRoute> {
        HomeScreenRoot(
            navigateToAddCar = navigateToAddCar,
        )
    }


}

@Serializable
data object HomeScreenRoute