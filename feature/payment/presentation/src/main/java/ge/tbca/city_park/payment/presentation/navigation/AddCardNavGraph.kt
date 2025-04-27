package ge.tbca.city_park.payment.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.payment.presentation.screen.add_balance.AddBalanceScreenRoot
import ge.tbca.city_park.payment.presentation.screen.add_card.AddCardScreenRoot
import ge.tbca.city_park.payment.presentation.screen.cards.CardsScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.paymentNavGraph(
    navigateBack: () -> Unit,
    navigateToAddCard: () -> Unit,
    onShowSnackBar: (String) -> Unit
) {
    composable<AddCardScreenRoute> {
        AddCardScreenRoot(
            navigateBack = navigateBack,
            onShowSnackBar = onShowSnackBar
        )
    }

    composable<CardsScreenRoute> {
        CardsScreenRoot(
            navigateBack = navigateBack,
            onShowSnackBar = onShowSnackBar,
            navigateToAddCard = navigateToAddCard
        )
    }

    composable<AddBalanceScreenRoute> {
        AddBalanceScreenRoot(
            onShowSnackbar = onShowSnackBar,
            navigateToAddCard = navigateToAddCard,
            navigateBack = navigateBack
        )
    }
}

@Serializable
data object AddCardScreenRoute

@Serializable
data object CardsScreenRoute

@Serializable
data object AddBalanceScreenRoute