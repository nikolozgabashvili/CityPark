package ge.tbca.city_park.presentation.features.language_settings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.domain.model.AppLanguage
import ge.tbca.city_park.presentation.core.design_system.components.button.radio_button.PrimaryRadioButton
import ge.tbca.city_park.presentation.core.design_system.components.horizontal_panel.HorizontalPanel
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.extensions.displayName
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun LanguageSettingsScreenRoot(
    navigateBack: () -> Unit,
    viewModel: LanguageSettingsViewModel = hiltViewModel()
) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

        when (effect) {
            LanguageSettingsEffect.NavigateBack -> navigateBack()
        }

    }

    LanguageSettingsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun LanguageSettingsScreen(
    state: LanguageSettingsState,
    onEvent: (LanguageSettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.change_language),
            startIcon = Icons.AutoMirrored.Default.ArrowBack,
            onStartIconClick = { }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        LazyColumn {
            items(state.appLanguages.size) { index ->
                val currentLanguage = state.appLanguages[index]
                val isSelected = currentLanguage == state.selectedAppLanguage
                val isLast = currentLanguage == state.appLanguages.last()
                HorizontalPanel(
                    title = currentLanguage.displayName(),
                    description = null,
                    startIcon = {
                        PrimaryRadioButton(
                            onClick = {
                                if (!isSelected) onEvent(
                                    LanguageSettingsEvent.LanguageSelected(
                                        currentLanguage
                                    )
                                )
                            },
                            isSelected = isSelected
                        )
                    },
                    endIcon = {
                        Box(
                            modifier = Modifier
                                .size(Dimen.size40)
                                .background(AppColors.secondaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = currentLanguage.flagEmoji)
                        }
                    },
                    hasUnderLine = !isLast,
                    onClick = {
                        if (!isSelected) onEvent(
                            LanguageSettingsEvent.LanguageSelected(
                                currentLanguage
                            )
                        )
                    }
                )
                if (!isLast) Spacer(modifier = Modifier.height(Dimen.size8))

            }
        }
    }
}

@Composable
@AppPreview
private fun LanguageSettingsScreenPreview() {
    AppTheme {
        LanguageSettingsScreen(
            state = LanguageSettingsState(
                appLanguages = AppLanguage.entries,
                selectedAppLanguage = AppLanguage.ENGLISH
            ),
            onEvent = {}
        )
    }
}