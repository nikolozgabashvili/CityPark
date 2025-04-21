package ge.tbca.city_park.settings.presentation


import ge.tbca.city_park.settings.domain.model.AppThemeOption
import ge.tbca.city_park.settings.domain.usecase.GetSavedThemeUseCase
import ge.tbca.city_park.settings.domain.usecase.SaveThemeUseCase
import ge.tbca.city_park.settings.presentation.theme_settings.screen.ThemeSettingsEffect
import ge.tbca.city_park.settings.presentation.theme_settings.screen.ThemeSettingsEvent
import ge.tbca.city_park.settings.presentation.theme_settings.screen.ThemeSettingsViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ThemeSettingsViewModelTest {

    private val getSavedThemeUseCase: GetSavedThemeUseCase = mockk()
    private val saveThemeUseCase: SaveThemeUseCase = mockk()
    private lateinit var viewModel: ThemeSettingsViewModel

    // imitation of datastore flow
    private val selectedThemeChannel = Channel<AppThemeOption>()


    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())

        coEvery { getSavedThemeUseCase() } returns selectedThemeChannel.receiveAsFlow()
        coEvery { saveThemeUseCase(any()) } just Runs


        viewModel = ThemeSettingsViewModel(
            getSavedThemeUseCase,
            saveThemeUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `BackButtonClicked in onEvent sends NavigateBack side effect`() = runTest {

        // Emit theme
        selectedThemeChannel.send(AppThemeOption.LIGHT)

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