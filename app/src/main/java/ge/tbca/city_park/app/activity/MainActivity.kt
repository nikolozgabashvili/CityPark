package ge.tbca.city_park.app.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core.designsystem.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import ge.tbca.city_park.app.ui.CityParkApplication
import ge.tbca.city_park.app.ui.rememberAppState
import ge.tbca.city_park.app.util.languageManager
import ge.tbca.city_park.auth.presentation.navigation.AuthNavGraphRoute
import ge.tbca.city_park.home.presentation.navigation.HomeScreenRoute
import ge.tbca.city_park.settings.domain.model.AppThemeOption
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        observeLanguage()

        splashScreen.setKeepOnScreenCondition { viewModel.state.isLoading }

        setContent {

            val themeMode = viewModel.state.themeOption

            val showDarkTheme =
                ((themeMode == AppThemeOption.SYSTEM) && isSystemInDarkTheme()) || (themeMode == AppThemeOption.DARK)

            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    lightScrim = Color.TRANSPARENT,
                    darkScrim = Color.TRANSPARENT,
                ) { showDarkTheme },
                navigationBarStyle = SystemBarStyle.auto(
                    lightScrim = lightScrim,
                    darkScrim = darkScrim,
                ) { showDarkTheme },
            )


            viewModel.state.isAuthorized?.let {isAuthorized->
                val startDestination = if (isAuthorized) HomeScreenRoute::class else AuthNavGraphRoute::class
                val appState = rememberAppState()
                AppTheme(darkTheme = showDarkTheme) {
                    CityParkApplication(
                        appState = appState,
                        startDestination = startDestination,
                        onSuccessfulAuth = {
                            viewModel.onEvent(MainActivityEvent.OnSuccessfulAuth)
                        }
                    )
                }
            }

        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase.languageManager.updateResources(newBase))
    }

    private fun observeLanguage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is MainActivityEffect.LanguageChanged -> {
                            recreate()
                        }
                    }

                }
            }
        }
    }
}

private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
