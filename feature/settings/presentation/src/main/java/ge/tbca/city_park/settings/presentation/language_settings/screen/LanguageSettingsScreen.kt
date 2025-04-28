package ge.tbca.city_park.settings.presentation.language_settings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.radio_button.PrimaryRadioButton
import com.example.core.designsystem.components.horizontal_panel.HorizontalPanel
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.settings.domain.model.AppLanguage
import ge.tbca.city_park.settings.presentation.R
import ge.tbca.city_park.settings.presentation.language_settings.extension.displayName

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
            onStartIconClick = {
                onEvent(LanguageSettingsEvent.NavigateBack)
            }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        LazyColumn {
            items(state.appLanguages) { language ->
                val isSelected = language == state.selectedAppLanguage
                val isLast = language == state.appLanguages.last()
                HorizontalPanel(
                    title = language.displayName(),
                    description = null,
                    startIcon = {
                        PrimaryRadioButton(
                            onClick = {
                                if (!isSelected) onEvent(
                                    LanguageSettingsEvent.LanguageSelected(
                                        language
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
                            Text(text = language.flagEmoji)
                        }
                    },
                    hasUnderline = !isLast,
                    onClick = {
                        if (!isSelected) onEvent(
                            LanguageSettingsEvent.LanguageSelected(
                                language
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