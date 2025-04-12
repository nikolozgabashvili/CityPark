package ge.tbca.city_park.presentation.features.theme_settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.core.design_system.components.items.theme_item.ThemeDetails
import ge.tbca.city_park.presentation.core.design_system.components.items.theme_item.ThemeItem
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun ThemeSettingsRoot(viewModel: ThemeSettingsViewModel = hiltViewModel()) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }

    ThemeSettingsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ThemeSettingsScreen(
    state: ThemeSettingsState,
    onEvent: (ThemeSettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.change_theme),
            startIcon = Icons.AutoMirrored.Default.ArrowBack,
            onStartIconClick = { }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        LazyColumn {
            items(state.themes.size, key = { index -> state.themes[index] }) { index ->
                ThemeItem(
                    onThemeSelected = { onEvent(ThemeSettingsEvent.ThemeSelected(it)) },
                    themeDetails = ThemeDetails(
                        themeName = state.themes[index],
                        selectedTheme = state.selectedTheme,
                        showUnderline = index != state.themes.lastIndex
                    )
                )
            }
        }
    }
}

@Composable
@AppPreview
private fun ThemeSettingsScreenPreview() {
    AppTheme {
        ThemeSettingsScreen(
            state = ThemeSettingsState(
                themes = listOf(AppThemeOption.SYSTEM, AppThemeOption.LIGHT, AppThemeOption.DARK),
                selectedTheme = AppThemeOption.SYSTEM
            ),
            onEvent = {}
        )
    }
}