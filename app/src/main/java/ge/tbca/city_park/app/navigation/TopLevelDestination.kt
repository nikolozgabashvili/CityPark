package ge.tbca.city_park.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import ge.tba.city_park.reservation.presentation.navigation.ReservationsRoute
import ge.tbca.city_park.R
import ge.tbca.city_park.home.presentation.navigation.HomeScreenRoute
import ge.tbca.city_park.more.presentation.navigation.MoreScreenRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    val route: KClass<*>
) {
    HOME(
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.home,
        route = HomeScreenRoute::class
    ),
    RESERVATION(
        selectedIcon = Icons.Filled.HistoryEdu,
        unselectedIcon = Icons.Outlined.HistoryEdu,
        iconTextId = R.string.history,
        route = ReservationsRoute::class
    ),
    MORE(
        selectedIcon = Icons.Rounded.Menu,
        unselectedIcon = Icons.Outlined.Menu,
        iconTextId = R.string.more,
        route = MoreScreenRoute::class
    )
}
