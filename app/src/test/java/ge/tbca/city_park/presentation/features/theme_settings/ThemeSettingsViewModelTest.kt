package ge.tbca.city_park.presentation.features.theme_settings

import ge.tbca.city_park.domain.core.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.domain.core.usecase.SaveThemeUseCase
import ge.tbca.city_park.domain.model.AppThemeOption
import ge.tbca.city_park.presentation.features.theme_settings.screen.ThemeSettingsEffect
import ge.tbca.city_park.presentation.features.theme_settings.screen.ThemeSettingsEvent
import ge.tbca.city_park.presentation.features.theme_settings.screen.ThemeSettingsViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
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

class ThemeSettingsViewModelTest {

    private val getSavedThemeUseCase: GetSavedThemeUseCase = mockk()
    private val saveThemeUseCase: SaveThemeUseCase = mockk()
    private lateinit var viewModel: ThemeSettingsViewModel

    // imitation of datastore flow
    private val selectedThemeFlow = Channel<AppThemeOption>(Channel.BUFFERED)

    @Before
    fun setUp() {

        coEvery { getSavedThemeUseCase() } returns selectedThemeFlow.receiveAsFlow()
        coEvery { saveThemeUseCase(any()) } just Runs

        // Emit theme before viewmodel is initialized
        selectedThemeFlow.trySend(AppThemeOption.LIGHT)

        viewModel = ThemeSettingsViewModel(getSavedThemeUseCase, saveThemeUseCase)
    }

    @Test
    fun `init collects theme from getSavedThemeUseCase and updates state`() = runTest {
        // Assert
        assertEquals(AppThemeOption.LIGHT, viewModel.state.selectedTheme)
    }

    @Test
    fun `ThemeSelected in onEvent calls saveThemeUseCase`() = runTest {
        viewModel.onEvent(ThemeSettingsEvent.ThemeSelected(AppThemeOption.LIGHT))

        // Verify that saveThemeUseCase was called with the correct theme
        coVerify { saveThemeUseCase(AppThemeOption.LIGHT) }
    }

    @Test
    fun `BackButtonClicked in onEvent sends NavigateBack side effect`() = runTest {
        var capturedEffect: ThemeSettingsEffect? = null

        val job = viewModel.effect
            .take(1)
            .onEach { capturedEffect = it }
            .launchIn(this)

        viewModel.onEvent(ThemeSettingsEvent.BackButtonClicked)

        // Wait for job to complete
        job.join()

        // Assert
        assertEquals(ThemeSettingsEffect.NavigateBack, capturedEffect)
    }
}