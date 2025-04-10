package ge.tbca.city_park.presentation.core.design_system.components.list.language_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.components.button.radio_button.PrimaryRadioButton
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    onLanguageSelected: (String) -> Unit,
    language: String,
    flagEmoji: String,
    selectedLanguage: String,
    showUnderline: Boolean
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onLanguageSelected(language) })
    {
        Row {
            PrimaryRadioButton(
                text = language,
                isSelected = language == selectedLanguage,
                onClick = { onLanguageSelected(language) }
            )
        }

        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = flagEmoji
        )

        if (showUnderline) {
            Divider(
                modifier = Modifier
                    .padding(start = Dimen.size50)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
@AppPreview
private fun LanguageItemPreview() {
    AppTheme {
        LanguageItem(
            onLanguageSelected = {},
            language = "ქართული",
            flagEmoji = "🇬🇪",
            selectedLanguage = "ქართული",
            showUnderline = true
        )
    }
}