package ge.tbca.city_park.presentation.features.language_settings.screen

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
import ge.tbca.city_park.domain.model.Language
import ge.tbca.city_park.presentation.core.design_system.components.items.language_item.LanguageDetails
import ge.tbca.city_park.presentation.core.design_system.components.items.language_item.LanguageItem
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun LanguageSettingsScreenRoot(viewModel: LanguageSettingsViewModel = hiltViewModel()) {

    CollectSideEffect(flow = viewModel.effect) { effect ->

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
            items(state.languages.size, key = { index -> state.languages[index] }) { index ->
                LanguageItem(
                    onLanguageSelected = { onEvent(LanguageSettingsEvent.LanguageSelected(it)) },
                    languageDetails = LanguageDetails(
                        language = state.languages[index],
                        selectedLanguage = state.selectedLanguage,
                        showUnderline = index != state.languages.lastIndex
                    ),
                )
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
                languages = listOf(Language.GEORGIAN, Language.ENGLISH),
                selectedLanguage = Language.ENGLISH
            ),
            onEvent = {}
        )
    }
}