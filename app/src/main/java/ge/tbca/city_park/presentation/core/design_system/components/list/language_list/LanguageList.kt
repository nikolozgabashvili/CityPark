package ge.tbca.city_park.presentation.core.design_system.components.list.language_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun LanguageList(
    modifier: Modifier = Modifier,
    onLanguageSelected: (String) -> Unit,
    languages: List<String>,
    flagEmojis: List<String>,
    selectedLanguage: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        languages.forEachIndexed { index, language ->
            LanguageItem(
                onLanguageSelected = onLanguageSelected,
                language = language,
                flagEmoji = flagEmojis[index],
                selectedLanguage = selectedLanguage,
                showUnderline = index != languages.lastIndex
            )
        }
    }
}

@Composable
@AppPreview
private fun LanguageListPreview() {
    AppTheme {
        LanguageList(
            onLanguageSelected = {},
            languages = listOf("ქართული", "English"),
            flagEmojis = listOf("🇬🇪", "🇺🇸"),
            selectedLanguage = "ქართული"
        )
    }
}