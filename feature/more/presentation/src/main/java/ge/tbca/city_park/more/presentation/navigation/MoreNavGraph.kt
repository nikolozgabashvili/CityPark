package ge.tbca.city_park.more.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.more.presentation.screen.MoreScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.moreNavGraph(
    navigateToSettings: () -> Unit,
    navigateToCars: () -> Unit,
    navigateToCards: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    composable<MoreScreenRoute> {
        MoreScreenRoot(
            navigateToSettings = navigateToSettings,
            navigateToCars = navigateToCars,
            navigateToCards = navigateToCards,
            navigateToProfile = navigateToProfile
        )
    }
}

@Serializable
data object MoreScreenRoute