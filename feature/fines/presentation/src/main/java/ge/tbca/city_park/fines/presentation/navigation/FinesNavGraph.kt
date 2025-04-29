package ge.tbca.city_park.fines.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.fines.presentation.screen.fines_screen.FinesScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.finesNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateToFineDetail: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    composable<FinesScreenRoute> {
        FinesScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateToFineDetail = navigateToFineDetail,
            navigateBack = navigateBack
        )
    }
}

@Serializable
data object FinesScreenRoute