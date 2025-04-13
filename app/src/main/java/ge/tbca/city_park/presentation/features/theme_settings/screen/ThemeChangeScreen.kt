package ge.tbca.city_park.presentation.features.theme_settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import ge.tbca.city_park.presentation.core.design_system.components.button.radio_button.PrimaryRadioButton
import ge.tbca.city_park.presentation.core.design_system.components.horizontal_panel.HorizontalPanel
import ge.tbca.city_park.presentation.core.design_system.components.image.IconWithBackground
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.extensions.displayIcon
import ge.tbca.city_park.presentation.core.extensions.displayName
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun ThemeChangeScreenRoot(
    navigateBack: () -> Unit,
    viewModel: ThemeSettingsViewModel = hiltViewModel()
) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

        when (effect) {
            ThemeSettingsEffect.NavigateBack -> navigateBack()
        }
    }

    ThemeChangeScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ThemeChangeScreen(
    state: ThemeSettingsState,
    onEvent: (ThemeSettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = Dimen.appPadding)
    ) {
        TopNavigationBar(
            modifier = Modifier.padding(horizontal = Dimen.appPadding),
            title = stringResource(R.string.change_theme),
            startIcon = Icons.AutoMirrored.Default.ArrowBack,
            onStartIconClick = { onEvent(ThemeSettingsEvent.BackButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        LazyColumn(contentPadding = PaddingValues(horizontal = Dimen.appPadding)) {
            items(state.themes.size) { index ->
                val currentTheme = state.themes[index]
                val isLast = currentTheme == state.themes.last()
                val isSelected = currentTheme == state.selectedTheme
                HorizontalPanel(
                    onClick = {
                        if (!isSelected) onEvent(ThemeSettingsEvent.ThemeSelected(currentTheme))
                    },
                    title = currentTheme.displayName(),
                    startIcon = {
                        PrimaryRadioButton(
                            onClick = {
                                if (!isSelected) onEvent(
                                    ThemeSettingsEvent.ThemeSelected(
                                        currentTheme
                                    )
                                )
                            },
                            isSelected = isSelected
                        )
                    },
                    endIcon = {
                        IconWithBackground(
                            icon = currentTheme.displayIcon()
                        )
                    },
                    hasUnderLine = !isLast
                )
                if (!isLast) Spacer(modifier = Modifier.height(Dimen.size8))

            }
        }
    }
}

@Composable
@AppPreview
private fun ThemeSettingsScreenPreview() {
    AppTheme {
        ThemeChangeScreen(
            state = ThemeSettingsState(
                themes = AppThemeOption.entries,
                selectedTheme = AppThemeOption.SYSTEM
            ),
            onEvent = {}
        )
    }
}