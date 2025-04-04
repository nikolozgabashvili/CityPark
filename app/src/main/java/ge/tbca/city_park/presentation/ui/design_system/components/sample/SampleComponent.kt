package ge.tbca.city_park.presentation.ui.design_system.components.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.util.AppPreview

@Composable
fun SampleComponent(
    modifier: Modifier = Modifier
) {

}

@AppPreview
@Composable
private fun SampleComponentPrev() {
    AppTheme {
        SampleComponent()
    }

}