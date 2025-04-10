package ge.tbca.city_park.presentation.features.language_settings.screen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.components.button.base.ButtonSize
import ge.tbca.city_park.presentation.core.design_system.components.button.text_button.PrimaryButton
import ge.tbca.city_park.presentation.core.design_system.components.list.language_list.LanguageList
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun LanguageSettingsScreenRoot(viewModel: LanguageSettingsViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }

    LanguageSettingsScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        scrollState = scrollState
    )
}

@Composable
private fun LanguageSettingsScreen(
    state: LanguageSettingsState,
    scrollState: ScrollState,
    onEvent: (LanguageSettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.change_language),
            startIcon = Icons.AutoMirrored.Default.ArrowBack,
            onStartIconClick = { }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        LanguageList(
            onLanguageSelected = { onEvent(LanguageSettingsEvent.LanguageSelected(it)) },
            languages = state.languages,
            flagEmojis = state.flagEmojis,
            selectedLanguage = state.selectedLanguage
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.LARGE,
            loading = state.isLoading,
            text = stringResource(R.string.save),
            onClick = { onEvent(LanguageSettingsEvent.SaveLanguageClicked) }
        )
    }
}

@Composable
@AppPreview
private fun LanguageSettingsScreenPreview() {
    AppTheme {
        LanguageSettingsScreen(
            state = LanguageSettingsState(
                languages = listOf("ქართული", "English"),
                flagEmojis = listOf("🇬🇪", "🇺🇸"),
                selectedLanguage = "English"
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}