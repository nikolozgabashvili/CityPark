package ge.tbca.city_park.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.example.core.designsystem.components.bottom_nav_item.BottomNavItem
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.transparent
import ge.tbca.city_park.app.navigation.TopLevelDestination

@Composable
fun CityParkBottomNavigation(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = destinations.isNotEmpty(),
        enter = fadeIn() + expandVertically(),
        exit = ExitTransition.None
    ) {
        Surface(
            modifier = modifier,
            color = AppColors.transparent,
            shadowElevation = Dimen.size1,
            shape = RoundedCornerShape(topStart = Dimen.size16, topEnd = Dimen.size16)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimen.size75),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                destinations.forEach { destination ->
                    val selected = currentDestination.isRouteInHierarchy(destination.route)

                    BottomNavItem(
                        selectedIcon = destination.selectedIcon,
                        unselectedIcon = destination.unselectedIcon,
                        label = stringResource(destination.iconTextId),
                        selected = selected,
                        onClick = { onNavigateToDestination(destination) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}