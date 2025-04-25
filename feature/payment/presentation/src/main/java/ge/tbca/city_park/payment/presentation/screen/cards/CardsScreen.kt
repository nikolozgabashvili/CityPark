package ge.tbca.city_park.payment.presentation.screen.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.component.CardItem

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
    // TODO pull to refresh
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.credit_cards),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(CardsEvent.NavigateBack) }
        )

        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.cardsList.isEmpty() -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.you_havent_added_any_cards),
                    style = TextStyles.bodyLarge,
                    color = AppColors.primary
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = Dimen.size50),
                    verticalArrangement = Arrangement.spacedBy(Dimen.size16)
                ) {
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