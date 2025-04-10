package ge.tbca.city_park.presentation.core.design_system.components.language_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.components.radio_button.PrimaryRadioButton
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
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimen.appPadding)
    ) {
        languages.forEachIndexed { index, language ->
            Box(modifier = Modifier.clickable { onLanguageSelected(language) }) {
                Row {
                    PrimaryRadioButton(
                        text = languages[index],
                        isSelected = language == selectedLanguage,
                        onClick = { onLanguageSelected(language) }
                    )
                }

                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = flagEmojis[index]
                )

                Divider(
                    modifier = Modifier
                        .padding(start = Dimen.size50)
                        .align(Alignment.BottomCenter)
                )
            }
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