package ge.tbca.city_park.presentation.features.settings

import ge.tbca.city_park.domain.core.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.features.settings.screen.SettingsEffect
import ge.tbca.city_park.presentation.features.settings.screen.SettingsEvent
import ge.tbca.city_park.presentation.features.settings.screen.SettingsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SettingsViewModelTest {

    private val getSavedThemeUseCase: GetSavedThemeUseCase = mockk()
    private lateinit var viewModel: SettingsViewModel

    // imitation of datastore flow
    private val selectedThemeFlow = Channel<AppThemeOption>(Channel.BUFFERED)

    @Before
    fun setUp() {

        coEvery { getSavedThemeUseCase() } returns selectedThemeFlow.receiveAsFlow()

        // Emit theme before viewmodel is initialized
        selectedThemeFlow.trySend(AppThemeOption.LIGHT)

        viewModel = SettingsViewModel(getSavedThemeUseCase)
    }

    @Test
    fun `init collects theme from getSavedThemeUseCase and updates state`() = runTest {
        // Assert
        assertEquals(AppThemeOption.LIGHT, viewModel.state.currentThemeMode)
    }

    @Test
    fun `BackButtonClicked in onEvent sends NavigateBack side effect`() = runTest {
        var capturedEffect: SettingsEffect? = null

        val job = viewModel.effect
            .take(1)
            .onEach { capturedEffect = it }
            .launchIn(this)

        viewModel.onEvent(SettingsEvent.BackButtonClicked)

        // Wait for job to complete
        job.join()

        // Assert
        assertEquals(SettingsEffect.NavigateBack, capturedEffect)
    }

    @Test
    fun `NavigateToLanguageSettings in onEvent sends NavigateToLanguageSettings side effect`() =
        runTest {
            var capturedEffect: SettingsEffect? = null

            val job = viewModel.effect
                .take(1)
                .onEach { capturedEffect = it }
                .launchIn(this)

            viewModel.onEvent(SettingsEvent.NavigateToLanguageSettings)

            // Wait for job to complete
            job.join()

            // Assert
            assertEquals(SettingsEffect.NavigateToLanguageSettings, capturedEffect)
        }

    @Test
    fun `NavigateToThemeSettings in onEvent sends NavigateToThemeSettings side effect`() = runTest {
        var capturedEffect: SettingsEffect? = null

        val job = viewModel.effect
            .take(1)
            .onEach { capturedEffect = it }
            .launchIn(this)

        viewModel.onEvent(SettingsEvent.NavigateToThemeSettings)

        // Wait for job to complete
        job.join()

        // Assert
        assertEquals(SettingsEffect.NavigateToThemeSettings, capturedEffect)
    }
}