package ge.tbca.city_park.presentation.core.design_system.components.list.settings_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview

@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    settingsDetails: List<SettingsDetail>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimen.appPadding)
    ) {
        LazyColumn {
            items(settingsDetails.size, key = { index -> settingsDetails[index].id }) { index ->
                SettingsItem(
                    onClick = onClick,
                    settingsDetail = settingsDetails[index]
                )
            }
        }
    }
}

@Composable
@AppPreview
private fun SettingsListPreview() {
    AppTheme {
        SettingsList(
            onClick = {},
            settingsDetails = listOf(
                SettingsDetail(
                    id = "language",
                    name = "ენა",
                    startIcon = Icons.Default.Language,
                    endText = "ქართული"
                ),
                SettingsDetail(
                    id = "theme",
                    name = "განათების რეჟიმი",
                    startIcon = Icons.Default.FlashlightOn,
                    endText = "თეთრი"
                )
            )
        )
    }
}