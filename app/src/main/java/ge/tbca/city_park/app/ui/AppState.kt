package ge.tbca.city_park.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
): AppState {

    return remember(navHostController) { AppState(navHostController) }

}


data class AppState(
    val navHostController: NavHostController
)