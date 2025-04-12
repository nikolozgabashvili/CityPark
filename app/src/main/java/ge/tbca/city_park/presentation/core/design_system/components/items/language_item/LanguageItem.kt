package ge.tbca.city_park.presentation.core.design_system.components.items.language_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ge.tbca.city_park.domain.model.Language
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.components.button.radio_button.PrimaryRadioButton
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.extensions.displayName

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    onLanguageSelected: (Language) -> Unit,
    languageDetails: LanguageDetails
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onLanguageSelected(languageDetails.language) })
    )
    {
        Row {
            PrimaryRadioButton(
                text = languageDetails.language.displayName(),
                isSelected = languageDetails.language == languageDetails.selectedLanguage,
                onClick = { onLanguageSelected(languageDetails.language) }
            )
        }

        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = languageDetails.language.flagEmoji
        )

        if (languageDetails.showUnderline) {
            Divider(
                modifier = Modifier
                    .padding(start = Dimen.size32)
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
            languageDetails = LanguageDetails(
                language = Language.GEORGIAN,
                selectedLanguage = Language.GEORGIAN,
                showUnderline = true
            ),
        )
    }
}