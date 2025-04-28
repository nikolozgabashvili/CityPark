package ge.tbca.city_park.settings.presentation.theme_settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.radio_button.PrimaryRadioButton
import com.example.core.designsystem.components.horizontal_panel.HorizontalPanel
import com.example.core.designsystem.components.image.IconWithBackground
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.settings.domain.model.AppThemeOption
import ge.tbca.city_park.settings.presentation.R
import ge.tbca.city_park.settings.presentation.theme_settings.extension.displayIcon
import ge.tbca.city_park.settings.presentation.theme_settings.extension.displayName

@Composable
fun ThemeSettingsScreenRoot(
    navigateBack: () -> Unit,
    viewModel: ThemeSettingsViewModel = hiltViewModel()
) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

        when (effect) {
            ThemeSettingsEffect.NavigateBack -> navigateBack()
        }
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
            items(state.themes) {theme->
                val isLast = theme == state.themes.last()
                val isSelected = theme == state.selectedTheme
                HorizontalPanel(
                    onClick = {
                        if (!isSelected) onEvent(ThemeSettingsEvent.ThemeSelected(theme))
                    },
                    title = theme.displayName(),
                    startIcon = {
                        PrimaryRadioButton(
                            onClick = {
                                if (!isSelected) onEvent(
                                    ThemeSettingsEvent.ThemeSelected(
                                        theme
                                    )
                                )
                            },
                            isSelected = isSelected
                        )
                    },
                    endIcon = {
                        IconWithBackground(
                            icon = theme.displayIcon()
                        )
                    },
                    hasUnderline = !isLast
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
        ThemeSettingsScreen(
            state = ThemeSettingsState(
                themes = AppThemeOption.entries,
                selectedTheme = AppThemeOption.SYSTEM
            ),
            onEvent = {}
        )
    }
}