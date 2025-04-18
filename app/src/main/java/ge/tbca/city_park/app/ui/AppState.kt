package ge.tbca.city_park.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState {

    return remember(navHostController) { AppState(navHostController, coroutineScope) }

}


data class AppState(
    val navHostController: NavHostController,
    val coroutineScope: CoroutineScope
)