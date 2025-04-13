package ge.tbca.city_park.app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ge.tbca.city_park.app.extension.isSystemInDarkTheme
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.features.theme_settings.screen.ThemeChangeScreenRoot
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var showDarkTheme by mutableStateOf(false)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(isSystemInDarkTheme(), viewModel.savedTheme) { systemDark, selectedTheme ->
                    systemDark && selectedTheme == AppThemeOption.SYSTEM || selectedTheme == AppThemeOption.DARK
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


        setContent {

            AppTheme(darkTheme = showDarkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                            .imePadding()
                    ) {
                        ThemeChangeScreenRoot(
                            navigateBack = {}
                        )
                    }

                }
            }
        }
    }
}

private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
