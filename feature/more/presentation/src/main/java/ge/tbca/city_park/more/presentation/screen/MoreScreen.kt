package ge.tbca.city_park.more.presentation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.setting_item.SettingItem
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.more.presentation.R

@Composable
fun MoreScreenRoot(
    navigateToSettings: () -> Unit,
    navigateToCars: () -> Unit,
    navigateToCards: () -> Unit,
    navigateToProfile: () -> Unit,
    viewModel: MoreViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            MoreEffect.NavigateToSettings -> navigateToSettings()
            MoreEffect.NavigateToCars -> navigateToCars()
            MoreEffect.NavigateToCards -> navigateToCards()
            MoreEffect.NavigateToProfile -> navigateToProfile()
        }
    }

    MoreScreen(
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun MoreScreen(
    scrollState: ScrollState,
    onEvent: (MoreEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(title = stringResource(R.string.more))

        Spacer(modifier = Modifier.height(Dimen.size32))


        SettingItem(
            title = stringResource(R.string.profile),
            hasUnderline = true,
            icon = Icons.Rounded.Person,
            onClick = { onEvent(MoreEvent.NavigateToProfile) }
        )


        Spacer(modifier = Modifier.height(Dimen.size8))

        SettingItem(
            title = stringResource(R.string.my_cars),
            hasUnderline = true,
            icon = Icons.Rounded.DirectionsCar,
            onClick = { onEvent(MoreEvent.NavigateToCars) }
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        SettingItem(
            title = stringResource(R.string.my_cards),
            hasUnderline = true,
            icon = Icons.Rounded.CreditCard,
            onClick = { onEvent(MoreEvent.NavigateToCards) }
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        SettingItem(
            title = stringResource(R.string.settings),
            hasUnderline = false,
            icon = Icons.Rounded.Settings,
            onClick = { onEvent(MoreEvent.NavigateToSettings) }
        )

    }
}

@AppPreview
@Composable
private fun MoreScreenPreview() {
    AppTheme {
        MoreScreen(
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}