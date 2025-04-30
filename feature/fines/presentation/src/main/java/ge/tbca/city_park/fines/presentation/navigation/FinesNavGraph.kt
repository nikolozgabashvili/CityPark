package ge.tbca.city_park.fines.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ge.tbca.city_park.fines.presentation.screen.fine_detail.FineDetailsScreenRoot
import ge.tbca.city_park.fines.presentation.screen.fines_screen.FinesScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.finesNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateToFineDetail: (Int) -> Unit,
    navigateBack: () -> Unit,
    onSuccessFinePayment: () -> Unit,
    navigateToAddCard: () -> Unit,
) {
    composable<FinesScreenRoute> {
        FinesScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateToFineDetail = navigateToFineDetail,
            navigateBack = navigateBack
        )
    }

    composable<FineDetailScreenRoute> {
        val fineId = it.toRoute<FineDetailScreenRoute>().fineId
        FineDetailsScreenRoot(
            fineId = fineId,
            onShowSnackBar = onShowSnackBar,
            onNavigateBack = navigateBack,
            onSuccess = onSuccessFinePayment,
            navigateToAddCard = navigateToAddCard,
        )
    }
}

@Serializable
data object FinesScreenRoute

@Serializable
data class FineDetailScreenRoute(val fineId: Int)