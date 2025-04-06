package ge.tbca.city_park.presentation.ui.design_system.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
annotation class AppPreview