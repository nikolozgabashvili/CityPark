package ge.tbca.city_park.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.Dimen
import ge.tbca.city_park.app.navigation.TopLevelDestination

@Composable
fun CityParkBottomNavigation(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    visible: Boolean,
    onNavigateToDestination: (TopLevelDestination) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = ExitTransition.None
    ) {

        NavigationBar(
            modifier = Modifier.clip(
                shape = RoundedCornerShape(
                    topStart = Dimen.size16,
                    topEnd = Dimen.size16
                )
            )
        ) {
            destinations.forEach { destination ->
                val selected = currentDestination.isRouteInHierarchy(destination.route)

                NavigationBarItem(

                    icon = {
                        Icon(
                            imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                            contentDescription = destination.name,
                            tint = if (selected) AppColors.inverseSurface else AppColors.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(destination.iconTextId),
                            color = if (selected) AppColors.inverseSurface else AppColors.onSurfaceVariant,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        )
                    },
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                )
            }
        }
    }
}