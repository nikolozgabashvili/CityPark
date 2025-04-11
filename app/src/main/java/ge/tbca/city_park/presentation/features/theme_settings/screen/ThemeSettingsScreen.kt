package ge.tbca.city_park.presentation.features.theme_settings.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.core.design_system.components.button.base.ButtonSize
import ge.tbca.city_park.presentation.core.design_system.components.button.text_button.PrimaryButton
import ge.tbca.city_park.presentation.core.design_system.components.list.theme_list.ThemeList
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun ThemeSettingsRoot(viewModel: ThemeSettingsViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }

    ThemeSettingsScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ThemeSettingsScreen(
    state: ThemeSettingsState,
    scrollState: ScrollState,
    onEvent: (ThemeSettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.change_theme),
            startIcon = Icons.AutoMirrored.Default.ArrowBack,
            onStartIconClick = { }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        ThemeList(
            onThemeSelected = { onEvent(ThemeSettingsEvent.ThemeSelected(it)) },
            themes = state.themes,
            endIcons = state.endIcons,
            selectedTheme = state.selectedTheme
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.LARGE,
            loading = state.isLoading,
            enabled = !state.isLoading,
            text = stringResource(R.string.save),
            onClick = { onEvent(ThemeSettingsEvent.SaveThemeClicked) }
        )
    }
}

@Composable
@AppPreview
private fun ThemeSettingsScreenPreview() {
    AppTheme {
        ThemeSettingsScreen(
            state = ThemeSettingsState(
                themes = listOf(AppThemeOption.LIGHT, AppThemeOption.DARK, AppThemeOption.SYSTEM),
                endIcons = listOf(
                    Icons.Default.WbSunny,
                    Icons.Default.ModeNight,
                    Icons.Default.Brightness6
                ),
                selectedTheme = AppThemeOption.SYSTEM
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}