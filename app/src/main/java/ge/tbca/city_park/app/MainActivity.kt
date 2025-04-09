package ge.tbca.city_park.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ge.tbca.city_park.app.ui.CityParkApplication
import ge.tbca.city_park.app.ui.rememberAppState
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState()
            AppTheme {
                CityParkApplication(appState)
            }
        }
    }
}
