package ge.tbca.city_park.parking.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect

@Composable
fun MapScreenRoot(
    viewModel: MapViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            else -> {}
        }
    }

    MapScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun MapScreen(
    state: MapState,
    onEvent: (MapEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // TODO map screen
    }
}

@AppPreview
@Composable
private fun MapScreenPreview() {
    MapScreen(
        state = MapState(),
        onEvent = {}
    )
}