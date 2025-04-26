package ge.tbca.city_park.settings.presentation.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Highlight
import androidx.compose.material.icons.rounded.Language
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
import ge.tbca.city_park.settings.presentation.R
import ge.tbca.city_park.settings.presentation.language_settings.extension.displayName
import ge.tbca.city_park.settings.presentation.theme_settings.extension.displayName

@Composable
fun SettingsScreenRoot(
    navigateToTheme: () -> Unit,
    navigateToLanguage: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

        when (effect) {
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
            title = stringResource(R.string.settings)
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Column {
            SettingItem(
                title = stringResource(R.string.language),
                description = state.currentLanguage.displayName(),
                hasUnderline = true,
                icon = Icons.Rounded.Language,
                onClick = {
                    onEvent(SettingsEvent.NavigateToLanguageSettings)
                }
            )
            Spacer(modifier = Modifier.height(Dimen.size8))

            SettingItem(
                title = stringResource(R.string.display_mode),
                description = state.currentThemeMode.displayName(),
                hasUnderline = false,
                icon = Icons.Rounded.Highlight,
                onClick = {
                    onEvent(SettingsEvent.NavigateToThemeSettings)
                }
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