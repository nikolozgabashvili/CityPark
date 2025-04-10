package ge.tbca.city_park.presentation.features.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.components.list.settings_list.SettingsDetail
import ge.tbca.city_park.presentation.core.design_system.components.list.settings_list.SettingsList
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

        SettingsList(
            onClick = { onEvent(SettingsEvent.SettingClicked(it)) },
            settingsDetails = state.settingsList
        )
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
                        id = "language",
                        name = "ენა",
                        startIcon = Icons.Default.Language,
                        endText = "ქართული"
                    ),
                    SettingsDetail(
                        id = "theme",
                        name = "განათების რეჟიმი",
                        startIcon = Icons.Default.FlashlightOn,
                        endText = "თეთრი"
                    )
                )
            ),
            onEvent = {}
        )
    }
}