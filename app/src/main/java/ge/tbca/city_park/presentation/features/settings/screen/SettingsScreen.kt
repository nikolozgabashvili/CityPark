package ge.tbca.city_park.presentation.features.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Highlight
import androidx.compose.material.icons.rounded.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.components.setting_item.SettingItem
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.extensions.displayName
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun SettingsScreenRoot(
    navigateBack: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToLanguage: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

        when (effect) {
            SettingsEffect.NavigateBack -> navigateBack()
            SettingsEffect.NavigateToLanguageSettings -> navigateToLanguage()
            SettingsEffect.NavigateToThemeSettings -> navigateToTheme()
        }

    }

    SettingsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun SettingsScreen(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.settings),
            startIcon = Icons.AutoMirrored.Default.ArrowBack,
            onStartIconClick = { onEvent(SettingsEvent.BackButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Column {
            SettingItem(
                title = stringResource(R.string.appLanguage),
                description = state.currentAppLanguage.displayName(),
                hasUnderline = true,
                icon = Icons.Rounded.Language,
                onClick = {
                    onEvent(SettingsEvent.BackButtonClicked)
                }
            )
            Spacer(modifier = Modifier.height(Dimen.size8))

            SettingItem(
                title = stringResource(R.string.lightning_mode),
                description = state.currentThemeMode.displayName(),
                hasUnderline = false,
                icon = Icons.Rounded.Highlight,
                onClick = {}
            )

        }
    }
}

@Composable
@AppPreview
private fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreen(
            state = SettingsState(

            ),
            onEvent = {}
        )
    }
}