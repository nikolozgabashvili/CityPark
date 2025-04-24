package ge.tbca.city_park.cars.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.cars.presentation.screen.add_car.AddCarScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.carNavGraph(
    navigateBack: () -> Unit,
    onShowSnackBar: (String) -> Unit,
) {
    composable<AddCarScreenRoute> {
        AddCarScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateBack = navigateBack
        )
    }
}

@Serializable
data object AddCarScreenRoute