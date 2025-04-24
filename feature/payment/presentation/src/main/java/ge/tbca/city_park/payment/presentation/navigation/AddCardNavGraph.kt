package ge.tbca.city_park.payment.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.payment.presentation.screen.add_card.AddCardScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addCardNavGraph(
    navigateBack: () -> Unit,
    onShowSnackBar: (String) -> Unit
) {
    composable<AddCardScreenRoute> {
        AddCardScreenRoot(
            navigateBack = navigateBack,
            onShowSnackBar = onShowSnackBar
        )
    }
}

@Serializable
data object AddCardScreenRoute