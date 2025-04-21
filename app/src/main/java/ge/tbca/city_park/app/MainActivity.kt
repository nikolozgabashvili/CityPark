package ge.tbca.city_park.app

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core.designsystem.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import ge.tbca.city_park.app.ui.CityParkApplication
import ge.tbca.city_park.app.ui.isSystemInDarkTheme
import ge.tbca.city_park.app.ui.rememberAppState
import ge.tbca.city_park.app.util.languageManager
import ge.tbca.city_park.settings.domain.model.AppThemeOption
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var showDarkTheme by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeTheme()
        observeLanguage()

        setContent {
            val appState = rememberAppState()
            AppTheme {
                CityParkApplication(appState)
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase.languageManager.updateResources(newBase))
    }

    private fun observeLanguage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.languageFlow.collect { _ ->
                    recreate()
                }
            }
        }
    }

    private fun observeTheme() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    isSystemInDarkTheme(),
                    viewModel.savedTheme
                ) { systemDark, selectedTheme ->
                    (systemDark && selectedTheme == AppThemeOption.SYSTEM) || selectedTheme == AppThemeOption.DARK
                }.onEach {
                    showDarkTheme = it
                }.collect { isDark ->
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.auto(
                            lightScrim = Color.TRANSPARENT,
                            darkScrim = Color.TRANSPARENT,
                        ) { isDark },
                        navigationBarStyle = SystemBarStyle.auto(
                            lightScrim = lightScrim,
                            darkScrim = darkScrim,
                        ) { isDark },
                    )

                }
            }
        }
    }
}

private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
