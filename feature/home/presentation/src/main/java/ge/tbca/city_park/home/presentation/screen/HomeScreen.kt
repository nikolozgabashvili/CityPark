@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.home.presentation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.error_wrapper.ErrorWrapper
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.home.presentation.R
import ge.tbca.city_park.user.presentation.component.UserBalanceCard

@Composable
fun HomeScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateToCars: () -> Unit,
    navigateToAddBalance: () -> Unit,
    navigateToProfile: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(viewModel.effect) { effect ->
        when (effect) {
            is HomeEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is HomeEffect.NavigateToAddBalance -> navigateToAddBalance()

            is HomeEffect.NavigateToProfile -> navigateToProfile()
        }

    }

    HomeScreen(
        state = state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    state: HomeState,
    scrollState: ScrollState,
    onEvent: (HomeEvent) -> Unit,
) {
    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = {
            onEvent(HomeEvent.Refresh)
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            TopNavigationBar(
                modifier = Modifier.padding( Dimen.appPadding),
                title = stringResource(R.string.home),
                endIcon = Icons.Rounded.Person,
                onEndIconClick = { onEvent(HomeEvent.NavigateToProfile) },
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState, enabled = !state.isLoading)
                    .padding(horizontal = Dimen.appPadding),
                verticalArrangement = Arrangement.spacedBy(Dimen.size20)


            ) {


                state.error?.let {
                    ErrorWrapper(
                        error = state.error.getString(),
                        onRetry = { onEvent(HomeEvent.Refresh) }
                    )
                }


                state.userBalance?.let {
                    UserBalanceCard(
                        balance = state.userBalance,
                        onAddBalanceClick = {
                            onEvent(HomeEvent.NavigateToAddBalance)
                        }
                    )
                }

            }
        }
    }

}


@AppPreview
@Composable
private fun HomeScreenPrev() {

    AppTheme {
        HomeScreen(
            state = HomeState(),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }

}