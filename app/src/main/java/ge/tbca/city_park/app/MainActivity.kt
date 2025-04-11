package ge.tbca.city_park.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.features.theme_settings.screen.ThemeSettingsRoot
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themePreferenceRepository: ThemePreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appThemeOption by themePreferenceRepository.selectedTheme.collectAsState(
                initial = AppThemeOption.SYSTEM
            )

            val darkTheme = when (appThemeOption) {
                AppThemeOption.LIGHT -> false
                AppThemeOption.DARK -> true
                AppThemeOption.SYSTEM -> isSystemInDarkTheme()
            }

            AppTheme(darkTheme = darkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                            .imePadding()
                    ) {

                        ThemeSettingsRoot()
                    }

                }
            }
        }
    }
}
