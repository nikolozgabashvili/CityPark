@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.payment.presentation.screen.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AddCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.empty_data_indicator.EmptyDataIndicator
import com.example.core.designsystem.components.pull_to_refresh.PullToRefreshWrapper
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.component.card_item.CardItem

@Composable
fun CardsScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit,
    viewModel: CardsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is CardsEffect.ShowSnackbar -> {
                val error = effect.message.getString(context)
                onShowSnackBar(error)
            }

            is CardsEffect.NavigateBack -> navigateBack()
        }
    }

    CardsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CardsScreen(
    state: CardsState,
    onEvent: (CardsEvent) -> Unit
) {
    PullToRefreshWrapper(
        isRefreshing = state.isLoading,
        onRefresh = { onEvent(CardsEvent.Refresh) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopNavigationBar(
                modifier = Modifier.padding(top = Dimen.appPadding),
                title = stringResource(R.string.credit_cards),
                startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                onStartIconClick = { onEvent(CardsEvent.NavigateBack) }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Dimen.size16)
            ) {
                if (state.noCards) {
                    item {
                        EmptyDataIndicator(
                            icon = Icons.Rounded.AddCard,
                            text = stringResource(R.string.you_havent_added_any_cards)
                        )
                    }
                } else if (state.cardsList.isNotEmpty()) {

                    items(items = state.cardsList, key = { it.id }) { card ->
                        CardItem(card = card)
                    }
                }
            }

        }
    }

}

@AppPreview
@Composable
private fun CardsScreenPreview() {
    AppTheme {
        CardsScreen(
            state = CardsState(),
            onEvent = {}
        )
    }
}