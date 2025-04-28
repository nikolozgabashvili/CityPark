package ge.tbca.city_park.parking.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.parking.presentation.screen.MapScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.mapNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateToAddBalance: () -> Unit,
    navigateToAddCar: () -> Unit,
) {

    composable<MapScreenRoute> {
        MapScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateToAddBalance = navigateToAddBalance,
            navigateToAddCar = navigateToAddCar,
        )
    }
}

@Serializable
data object MapScreenRoute