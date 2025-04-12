package ge.tbca.city_park.presentation.features.settings.screen

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
import ge.tbca.city_park.domain.model.Settings
import ge.tbca.city_park.presentation.core.design_system.components.items.settings_item.SettingsDetail
import ge.tbca.city_park.presentation.core.design_system.components.items.settings_item.SettingsItem
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun SettingsScreenRoot(viewModel: SettingsViewModel = hiltViewModel()) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

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

        LazyColumn {
            items(
                state.settingsList.count(),
                key = { index -> state.settingsList[index].id }) { index ->
                SettingsItem(
                    onClick = { onEvent(SettingsEvent.SettingClicked(state.settingsList[index].id)) },
                    settingsDetail = SettingsDetail(
                        state.settingsList[index].id,
                        state.settingsList[index].name,
                        state.settingsList[index].endText
                    )
                )
            }
        }
    }
}

@Composable
@AppPreview
private fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreen(
            state = SettingsState(
                settingsList = listOf(
                    SettingsDetail(
                        id = Settings.LANGUAGE,
                        name = "ენა",
                        endText = "ქართული"
                    ),
                    SettingsDetail(
                        id = Settings.THEME,
                        name = "განათების რეჟიმი",
                        endText = "თეთრი"
                    )
                )
            ),
            onEvent = {}
        )
    }
}