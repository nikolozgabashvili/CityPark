package ge.tbca.city_park.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ge.tbca.city_park.app.navigation.AppNavHost
import kotlinx.coroutines.launch

@Composable
fun CityParkApplication(
    appState: AppState
) {
    val snackbarHostState = remember { SnackbarHostState() }

    CityParkApplication(
        appState,
        snackbarHostState
    )

}

@Composable
fun CityParkApplication(
    appState: AppState,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = appState.coroutineScope

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState,
                modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
        ) {
            AppNavHost(appState, onShowSnackBar = { message ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            })
        }

    }
}