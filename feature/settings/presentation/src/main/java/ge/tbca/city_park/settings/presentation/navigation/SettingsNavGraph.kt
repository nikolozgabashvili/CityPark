package ge.tbca.city_park.settings.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.settings.presentation.language_settings.screen.LanguageSettingsScreenRoot
import ge.tbca.city_park.settings.presentation.settings.screen.SettingsScreenRoot
import ge.tbca.city_park.settings.presentation.theme_settings.screen.ThemeSettingsScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.settingsNavGraph(
    navigateBack: () -> Unit,
    navigateToLanguageSettings: () -> Unit,
    navigateToThemeSettings: () -> Unit
) {
    composable<SettingsScreenRoute> {
        SettingsScreenRoot(
            navigateToTheme = navigateToThemeSettings,
            navigateToLanguage = navigateToLanguageSettings
        )
    }

    composable<LanguageSettingsScreenRoute> {
        LanguageSettingsScreenRoot(
            navigateBack = navigateBack
        )
    }

    composable<ThemeSettingsScreenRoute> {
        ThemeSettingsScreenRoot(
            navigateBack = navigateBack
        )
    }
}

@Serializable
data object SettingsScreenRoute

@Serializable
data object LanguageSettingsScreenRoute

@Serializable
data object ThemeSettingsScreenRoute