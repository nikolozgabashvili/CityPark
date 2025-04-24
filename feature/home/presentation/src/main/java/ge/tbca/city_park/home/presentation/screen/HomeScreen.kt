package ge.tbca.city_park.home.presentation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            else -> {}
        }
    }

    HomeScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
    scrollState: ScrollState,
    onEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState, enabled = !state.isLoading)
            .padding(Dimen.appPadding)
    ) {

    }
}

@AppPreview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        scrollState = rememberScrollState(),
        onEvent = {}
    )
}